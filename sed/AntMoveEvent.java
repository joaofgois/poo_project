package sed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import antColony.IAntColony;
import weighted_graph.WeightedGraph;
import expRandom.ExtRandom;

public class AntMoveEvent<T> extends Event{

    private T antNext; // proxima posicao/posicao atual depende se estamos antes ou de
    private int antId;
    private IAntColony<T> antcolony;
    protected float alpha,beta,delta, pheroLevel;
    private WeightedGraph<T, Integer> graph;
    private ExtRandom rand;


    //constructor
    public AntMoveEvent(float time, IAntColony<T> antcolony, int antId){
        super(time);
        rand = ExtRandom.getInstance();
        this.antcolony = antcolony;
        this.antId = antId;
    }

    

    /**
     * Take the necessay actions to simulate the Event
     */
    @Override
    public void simulateEvent() {
        
        //move to next location
        antcolony.addAntPath(antId, antNext);

        //check if Hamilton Cycle is complete---------------PODEMOS POR ISTO FORA DA CLASSE, TALVEZ COM O STRATEGY PATTERN (ou nao)
        if (checkHamiltonCycle()){
            
            //simulacao e phero
            int w = 0;
            int miu=0;
            List<T> path = new ArrayList<>(antcolony.getAntPath(antId));

            //calculate total path weight
            for(int i=0; i<antcolony.getAntPath(antId).size()-1;i++){
                 
                w += graph.getEdgeWeight(path.get(i),path.get(i+1));
            }

            for(int i=0; i<antcolony.getAntPath(antId).size()-1;i++){

                antcolony.setPheromone(path.get(i),path.get(i+1),(pheroLevel*w)/miu); // miu= peso do grafo, por fazer....!!!!!!!!!!
                //falta enviar para o pec o coiso da evaporacao
            }
            antcolony.removeLastAntPath(antId);
            
            //falta funcao para mandar path e peso para o ACOSIMULATOR.storeCycle()

            // reset formiga
            
            antcolony.resetAnt(antId);
            antcolony.addAntPath(antId, antcolony.getAntPosition(antId));
            antNext = nextAntMove();
            //add new event to PEC 

            //falta pec.adddEvPEC(new AntMoveEvent(time + rand.nextExp( delta * graph.getEdgeWeight( antcolony.getAntPosition(antId), antNext) )));

        }
        else{
            //choose next location
            antNext = nextAntMove();
            T aux;
            //if ant has already visited the location, it must revert back
            if (antcolony.getAntPath(antId).contains(antNext)){
                while(true){
                    aux = antcolony.getAntPosition(antId);
                    if (antNext == aux){
                        antcolony.removeLastAntPath(antId);
                        break;
                    }
                    antcolony.removeLastAntPath(antId);
                    aux = antcolony.getAntPosition(antId);
                }
            }
            //add new event to PEC 

            //falta pec.adddEvPEC(new AntMoveEvent(time + rand.nextExp( delta * graph.getEdgeWeight( antcolony.getAntPosition(antId), antNext) )));  
            
        }
        

    }

    private boolean checkHamiltonCycle(){
        if (antcolony.getAntPosition(antId) == antcolony.colonyNest() && antcolony.getAntPath(antId).size() > graph.nrVertices()-1){
            return true;
        }
        return false;
    }

    private T nextAntMove(){
        
        float ci_total=0;

        //get locations already visited
        List<T> temp = antcolony.getAntPath(antId);
        Set<T> path = new HashSet<T>(temp);
        //get possible next locations
        Set<T> possible_temp = graph.getAdjacency(antcolony.getAntPosition(antId));
        //remove visited locations from possible next locations
        possible_temp.removeAll(path);
        
        //if there arent non-visited locations, must choose a visited node instead
        if (possible_temp.size() == 0){
            possible_temp = graph.getAdjacency(antcolony.getAntPosition(antId));
            possible_temp.remove(antcolony.getAntPosition(antId));

            //FALTA CASO TENHA QUE REMOVER CICLOS!!!!!!!!!!!!!!!!!!!!!
        }
        List<T> possible = new ArrayList<T>();
        possible.addAll(possible_temp);

        float[] probabilityMoves = new float[possible.size()];

        //calculate probabilities for each possible next move
        for (int i = 0; i < possible.size(); i++) {
            
            probabilityMoves[i]= calculateProb(possible.get(i));
            ci_total+= probabilityMoves[i];
            
        }
        //normalize the probabilities
        for (int i = 0; i < possible.size(); i++) {
            
            probabilityMoves[i]/=ci_total;  
        }
        
        //escolher no (ja se calculou as probabilidades dos nos nao visitados)
        int index = rand.chooseRand(probabilityMoves);        
        return possible.get(index);
    }

    private float calculateProb(T v2){
       
       return (alpha+ antcolony.getPheromone(antcolony.getAntPosition(antId),v2) )/(beta + graph.getEdgeWeight(antcolony.getAntPosition(antId) ,v2));
    }


}

// 1-localizacao 2-ver adjacentes 3- ver se sao nao visitados um a um 4a)- se sim calcular logo a Probabilidade e meter na string
// 4b) ignorar 5-se a string estiver fazia pegar em todos os adjacentes e escolher um random
// 6- escolheu guardar na variavel antNext, calcular a duracao da viagem com o expRandom e enviar para a PEC


