/**
 * 
 */
package main;

/**
 * Object used to generate new processes for the simulation.
 * @author ADASE
 *
 */
public class ProcessGenerator {
	int seed = 0;
	int numProcesses;
	
	
	public ProcessGenerator(int numProcesses) {
		super();
		this.numProcesses = numProcesses;
	}
	
	public ProcessGenerator() {
		super();
		this.numProcesses = -1;
	}
	
	/**
	 * Generates a new process and assigns it a PID
	 * @return Process
	 */
	public Process generateProcess() {
		Process newProcess = new Process(this.seed);
		this.seed = this.seed + 1;
		return newProcess;
	}
	
	/**
	 * Checks if there are more processes to generate
	 * @return Boolean
	 */
	public boolean hasMore() {
		if (this.numProcesses == -1){
			return true;
		}
		if(this.seed >= this.numProcesses) {
			// if we have generated the max processes then no more
			return false;
		}else {
			return true;
		}
	}

}
