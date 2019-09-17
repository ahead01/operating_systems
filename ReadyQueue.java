/**
 * 
 */
package main;

import java.util.ArrayList;

/**
 * Object representation of the Ready Queue.
 * @author ADASE
 *
 */
public class ReadyQueue {
	int size;
	ArrayList<Process> processQueue;
	//Process[] readyQueue;
	
	
	/**
	 * Instantiate the Ready Queue
	 * @param size The number of processes the queue can hold
	 */
	public ReadyQueue(int size) {
		super();
		this.size = size;
		ArrayList<Process> queue = new ArrayList<Process>();
		this.processQueue = queue;
	}
	
	
	/**
	 * Gets the next processes to be executed off the queue
	 * @return null is if the queue is empty
	 * otherwise returns the oldest process on the queue.
	 */
	public Process getNextProcess() {
		int lastIdx = this.processQueue.size() - 1;
		if (lastIdx >= 0) {
			return this.processQueue.remove(0);
		}else {
			return null;
		}	
	}
	
	/**
	 * Adds a new process to the queue if there is room
	 * @param newProcess Process to add to the queue
	 * @return A -1 if the queue is full or the size of the queue after 
	 * adding the new process 
	 */
	public int addProcess(Process newProcess) {
		if( this.processQueue.size() >= this.size) {
			return -1;
		}else {
			this.processQueue.add(newProcess);
			return this.processQueue.size();
		}
		
	}

	/**
	 * Checks to see if the queue has room
	 * @return True if it's not full False if it is (boolean)
	 */
	public boolean isNotFull() {
		if( this.processQueue.size() >= this.size) {
			return false;
		}else {
			return true;
		}
	}

	/**
	 * Checks to see if the queue is not empty
	 * @return False if it is empty, True if it is empty
	 */
	public boolean isNotEmpty() {
		if( this.processQueue.size() == 0) {
			return false;
		}else {
			return true;
		}
	}

	
	/**
	 * Gets the size of the queue
	 * @return The queue size
	 */
	public int size() {
		// TODO Auto-generated method stub
		return this.processQueue.size();
	}


	public Process get(int i) {
		// TODO Auto-generated method stub
		return this.processQueue.get(i);
	}


	public void set(int i, Process tempProcess) {
		// TODO Auto-generated method stub
		this.processQueue.set(i, tempProcess);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReadyQueue [size=" + size + ", processQueue=" + processQueue.toString() + "]";
	}
	
	
/*
	public ReadyQueue delayAll(int timeUnits) {
		//ArrayList<Process> tempQueue = getProcessQueue();
		for(int i = 0; i < processQueue.size();i++) {
			Process tempProcess = processQueue.get(i);
			tempProcess.delay(timeUnits);
			processQueue.set(i, tempProcess);
		}
		
	}
*/	
	
	
	

}
