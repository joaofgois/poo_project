package tspACO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import antColony.*;
import expRandom.ExtRandom;
import sed.*;

/**
 * Event that moves the ant from node to node.<p>
 * <strong>T</strong> is the data type for the graph vertices labels.
 */
public class AntMoveEvent<T> extends Event{

    private T antNext; // proxima posicao/posicao atual depende se estamos antes ou de
    private int antId;
    private IAntColony<T> antcolony;
    //protected float alpha,beta,delta, pheroLevel;
    //private WeightedGraph<T, Integer> graph;
    private ExtRandom rand;
    private Parameters<T,Integer> parameters;
    private ISimulator simulator;


    //constructor
    public AntMoveEvent(float time, ISimulator simulator, IAntColony<T> antcolony, int antId, T antNext, Parameters<T,Integer> parameters){
        super(time);
        rand = ExtRandom.getInstance();
        this.antcolony = antcolony;
        this.antId = antId;
        this.antNext = antNext;
        this.parameters = parameters;
        this.simulator = simulator;
    }

    

    /**
     * Take the necessary actions to simulate the Event
     */
    @Override
    public void simulateEvent() {
        //parent.AntMoveEvCounter ++


        //move to next location
        antcolony.addAntPath(antId, antNext);

        //check if Hamilton Cycle is complete
        if (checkHamiltonCycle()){
            
            //simulacao e phero
            int w = 0;
            List<T> path = new ArrayList<>(antcolony.getAntPath(antId));

            //calculate total path weight
            for(int i=0; i<antcolony.getAntPath(antId).size()-1;i++){
                w += parameters.graph.getEdgeWeight(path.get(i),path.get(i+1));
            }
            //lay down the pheromones
            for(int i=0; i<antcolony.getAntPath(antId).size()-1;i++){
            	//set pheromone value
            	
                if (antcolony.getPheromone(path.get(i),path.get(i+1)) == 0) {
                	//create new evaporation event
                	simulator.addEvPEC(new PheroEvent<T>((float) (time + rand.nextExp(parameters.eta)), simulator, antcolony, parameters, path.get(i), path.get(i+1)));
                }
                antcolony.setPheromone(path.get(i),path.get(i+1),(parameters.pheroLevel*w)/parameters.miu);
                
            }
            antcolony.removeLastAntPath(antId);
            
            //store cycle
            parameters.storeCycle(new ArrayList<>(antcolony.getAntPath(antId)), w);

            // reset formiga
            
            antcolony.resetAnt(antId);
            antcolony.addAntPath(antId, antcolony.getAntPosition(antId));
        }
    
        //choose next location
        antNext = nextAntMove();
        T aux;
        //if ant has already visited the location, it must revert back
        if (antcolony.getAntPath(antId).contains(antNext)){
        	if(antNext != antcolony.colonyNest() || antcolony.getAntPath(antId).size() != parameters.graph.nrVertices()){	
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
        }
        
        //add new event to PEC 
        this.time += rand.nextExp( parameters.delta * parameters.graph.getEdgeWeight( antcolony.getAntPosition(antId), antNext));
        simulator.addEvPEC(this);  

    }

    private boolean checkHamiltonCycle(){
        if (antcolony.getAntPosition(antId) == antcolony.colonyNest() && antcolony.getAntPath(antId).size() > parameters.graph.nrVertices()-1){
            return true;
        }
        return false;
    }

    private T nextAntMove(){
    	float[] probabilityMoves;
    	List<T> possible = new ArrayList<T>();
        
        float ci_total=0;

        //get locations already visited
        List<T> temp = antcolony.getAntPath(antId);
        Set<T> path = new HashSet<T>(temp);
        //get possible next locations
        Set<T> adjacent = parameters.graph.getAdjacency(antcolony.getAntPosition(antId));
        //remove visited locations from possible next locations
        Set<T> possible_temp = new HashSet<T>(adjacent);
        possible_temp.removeAll(path);
        
        //if there arent non-visited locations, must choose a visited node instead
        if (possible_temp.size() == 0){
            possible_temp = parameters.graph.getAdjacency(antcolony.getAntPosition(antId));
            possible_temp.remove(antcolony.getAntPosition(antId));
            
            possible.addAll(possible_temp);
            
            probabilityMoves = new float[possible.size()];
            
            for (int i = 0; i < possible.size(); i++) {
            	probabilityMoves[i] = (float) 1/possible.size();
            }
        } else {
            possible.addAll(possible_temp);

            probabilityMoves = new float[possible.size()];
        	//calculate probabilities for each possible next move
        	for (int i = 0; i < possible.size(); i++) {
        		
        		probabilityMoves[i]= calculateProb(possible.get(i));
        		ci_total+= probabilityMoves[i];
        		
        	}
        	//normalize the probabilities
        	for (int i = 0; i < possible.size(); i++) {
        		
        		probabilityMoves[i]/=ci_total;  
        	}
        }

        //escolher no (ja se calculou as probabilidades dos nos nao visitados)
        int index = rand.chooseRand(probabilityMoves);        
        return possible.get(index);
    }

    private float calculateProb(T v2){
       
       return (parameters.alpha+ antcolony.getPheromone(antcolony.getAntPosition(antId),v2) )/(parameters.beta + parameters.graph.getEdgeWeight(antcolony.getAntPosition(antId) ,v2));
    }

}

// 1-localizacao 2-ver adjacentes 3- ver se sao nao visitados um a um 4a)- se sim calcular logo a Probabilidade e meter na string
// 4b) ignorar 5-se a string estiver fazia pegar em todos os adjacentes e escolher um random
// 6- escolheu guardar na variavel antNext, calcular a duracao da viagem com o expRandom e enviar para a PEC


