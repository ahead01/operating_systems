/**
 * 
 */
package main;

import java.util.Random;

/**
 * Object representation of a process.
 * @author ADASE
 *
 */
public class Process {
	int pid;
	//double delayTime;
	double finshTime;
	double startTime;
	int executionUnits;
	int unitsExecuted;

	
	public Process(int pid) {
		super();
		this.pid = pid;
		Random r = new Random();
		int low = 2; //2
		int high = 7;//7
		int result = r.nextInt(high-low) + low;
		//this.intervalTime = result;
		//result = r.nextInt(high-low) + low;
		this.executionUnits = result;
		//this.finshTime = -1;
	}
	
	/**
	 * Get the number of units left to be executed.
	 * @return Number of units left to execute
	 */
	public int execUnitsLeft() {
		return this.executionUnits - this.unitsExecuted;
	}
	
	/**
	 * Method used by the CPU to simulate execution of the process. Increments timeExecuted by the number of units executed.
	 * @param quantum The maximum number of units to execute based on the CPU's provided quantum value
	 * @return Number of units executed. This will be the lesser value between the quantum and the processes'
	 * number of units left to be executed
	 */
	public int execute(int quantum) {
		int currentUnitsExecuted = this.unitsExecuted;
		int newExecuted = quantum + currentUnitsExecuted;
		if(newExecuted > this.executionUnits) {
			this.unitsExecuted = this.executionUnits;
			return this.executionUnits - currentUnitsExecuted;
		}else {
			this.unitsExecuted = newExecuted;
			return quantum;
		}
	}
	
	/*
	 * Increments the delay count by the specified number of units.
	 * @param timeUnits Number of units of time to increment by
	 */
	/*
	public void delay(double timeUnits) {
		setDelayTime(getDelayTime() + timeUnits);
	}
*/
	
	
	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}


	/*
	 * @return the delayTime
	*/
	public double getDelayTime() {
		return ((this.finshTime - this.startTime) - this.getTimeExecuted());
	}
	
	

	/*
	 * @param delayTime the delayTime to set
	 
	public void setDelayTime(double delayTime) {
		this.delayTime = delayTime;
	}
	*/

	/**
	 * @return the executionUnits
	 */
	public int getExecutionUnits() {
		return executionUnits;
	}

	/**
	 * @param executionUnits the executionUnits to set
	 */
	public void setExecutionUnits(int executionUnits) {
		this.executionUnits = executionUnits;
	}

	/**
	 * @return the timeExecuted
	 */
	public double getTimeExecuted() {
		return this.unitsExecuted * .25;
		//return ((this.finshTime - this.startTime) - this.delayTime);
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}

	/**
	 * @param unitsExecuted the unitsExecuted to set
	 */
	public void setUnitsExecuted(int unitsExecuted) {
		this.unitsExecuted = unitsExecuted;
	}

	/**
	 * @return the timeExecuted
	 */
	public int getUnitsExecuted() {
		return executionUnits;
	}



	/**
	 * @return the finshTime
	 */
	public double getFinshTime() {
		return finshTime;
	}

	/**
	 * @param finshTime the finshTime to set
	 */
	public void setFinshTime(double finshTime) {
		this.finshTime = finshTime;
	}

	/**
	 * @return Value indicating the time the process was introduced to the system.
	 */
	public double getStartTime() {
		return startTime;
	}

	/**
	 * Set the start time.
	 * @param startTime Value indicating the time the process was introduced to the system.
	 */
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Process [pid=" + pid + ", delayTime=" + this.getDelayTime() + ", finshTime="
				+ finshTime + ", startTime=" + startTime + ", executionUnits=" + executionUnits + "]";
	}


	
	

}
