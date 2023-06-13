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
    protected float alpha,beta,delta;
    private WeightedGraph<T, Integer> graph;
    private float PheroLevel;
    private ExtRandom rand;


    //constructor
    public AntMoveEvent(){
        rand = ExtRandom.getInstance();
    }

    

    /**
     * Take the necessay actions to simulate the Event
     */
    @Override
    public void simulateEvent() {
        
        String hamilton_cycle;
        //move to next location
        antcolony.addAntPath(antId, antNext);

        //check if Hamilton Cycle is complete---------------PODEMOS POR ISTO FORA DA CLASSE, TALVEZ COM O STRATEGY PATTERN (ou nao)
        if (checkHamiltonCycle()){
            
            //simulacao
            
            antcolony.removeLastAntPath(antId);
            
            for(int i=0; ;){

            
            }

            //phero
            for (iterable_type iterable_element : iterable) {
                
            }

            //formiga
            

        }
        else{
            //choose next location
            antNext = nextAntMove();
            //add new event to PEC 

            // pec.adddEvPEC(new AntMoveEvent(time + rand.nextExp( delta * graph.getEdgeWeight( antcolony.getAntPosition(antId), antNext) )));  
            
        }
        

    }

    private boolean checkHamiltonCycle(){
        if (antcolony.getAntPosition(antId) == antcolony.colonyNest() && antcolony.getAntPath(antId).size() > 1){
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
        
        //if there are no non-visited locations, must choose a visited onde instead
        if (possible_temp.size() == 0){
            possible_temp = graph.getAdjacency(antcolony.getAntPosition(antId));
            possible_temp.remove(antcolony.getAntPosition(antId));
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


