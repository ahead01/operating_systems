package main;

/**
 * Object representation of a CPU.
 * The CPU can execute 4 units of work in 1 unit of time
 * @author ADASE 
 *
 */
public class CPU {

	/**
	 * CPU comment
	 */
	Process currentProcess;
	int quantum;
	boolean isFree;
	
	
	/**
	 * @param quantum The value of for the CPU's quantum
	 */
	public CPU(int quantum) {
		super();
		this.quantum = quantum;
		this.isFree = true;
		this.currentProcess = null;
	}

	/**
	 * Get the PID of the current process on the CPU
	 * @return The currentPID or -1 if no process
	 */
	public int getCurrentPID() {
		if(this.currentProcess != null) {
			return this.currentProcess.getPid();
		}else {
			return -1;
		}
	}

	/**
	 * Loads the new process on the CPU
	 * Takes .25 units of time
	 * @param newProcess The new process to load onto CPU
	 * @return The prior process or null if no process was on CPU
	 */
	public Process loadProcess(Process newProcess) {
		if(getCurrentPID() != -1) {
			Process tempProcess = this.currentProcess;
			this.currentProcess = newProcess;
			return tempProcess;
		}else {
			this.currentProcess = newProcess;
			return null;
		}
	}
	
	/**
	 * Unloads the current process by setting it to null
	 * Takes .25 units of time
	 * @return The current process object
	 */
	public Process unloadProcess() {
		if(getCurrentPID() != -1) {
			Process tempProcess = this.currentProcess;
			this.currentProcess = null;
			return tempProcess;
		}else {
			return null;
		}
		
	}
	
	/**
	 * Executes the current process for 1 unit of work
	 * The CPU can execute 4 units of work in 1 unit of time
	 * @return Either the number of units left
	 *    to process for the current process or -1 if no more needed
	 */
	public int executeCPU() {
		System.out.print(String.format("CPU: Executing process %d :", getCurrentPID()));
		System.out.println(this.currentProcess.toString());
		return currentProcess.execute(this.quantum);
	}
	
	
}
