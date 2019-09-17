package main;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main
 * 
 * @author ADASE
 */
class ProcessMgmtApp {
	
	/**
	 * CPU speed is simulated to 2.5 GHz
	 * 
	 * @param args
	 * @param execution
	 * @return
	 */

	public static String[] mainRun(String[] args, int execution) {
		/* Start - Hyper Parameters */
		//int numOfProc = 3;
		int time = 1000;
		int readyQueueSize = 2;
		int cpuQuantum = 2;
		int iterationLimit = 50;
		double cycles = 0;
		int iterationDelay = 0;
		/* End - Hyper Parameters */

		/* Start - Process command line args */
		String usage = "Command Line Args\n\t-p Time limit (seconds).\n\t-q Ready Queue Size.\n\t-c CPU Quantum.\n\t-l Limit on Iterations\n\t-d Iteration delay (ms)\n\t-h Show Help";
		String helpPatternStr = "-h";
		Pattern helpPattern = Pattern.compile(helpPatternStr);
		String numProcStr = "-p";
		Pattern numProcPattern = Pattern.compile(numProcStr);
		String readyQueueStr = "-q";
		Pattern readyQueuePattern = Pattern.compile(readyQueueStr);
		String cpuStr = "-c";
		Pattern cpuPattern = Pattern.compile(cpuStr);
		String limitStr = "-l";
		Pattern limitPattern = Pattern.compile(limitStr);
		String numStr = "(-*\\d+)";
		Pattern numPattern = Pattern.compile(numStr);
		String delayStr = "-d";
		Pattern delayPattern = Pattern.compile(delayStr);
		if (args.length > 0) {
			System.out.println("Found command line args");
			for (int i = 0; i < args.length; i++) {
				String s = args[i];

				Matcher helpMatcher = helpPattern.matcher(s);
				boolean matchesHelp = helpMatcher.matches();
				if (matchesHelp) {
					System.out.println(s);
					System.out.println(usage);
					String[] results = { usage };
					return (results);
					// System.exit(0);
				} else {
					System.out.println(s + " " + args[i + 1]);
					Matcher numProcMatcher = numProcPattern.matcher(s);
					if (numProcMatcher.matches()) {
						i++;
						s = args[i];
						Matcher numMatcher = numPattern.matcher(s);
						if (numMatcher.matches()) {
							//System.out.println(String.format("Setting Number of Processes to %s.", s));
							System.out.println(String.format("Setting Time Limit to %s.", s));
							time = Integer.parseInt(s);
							time = 1000 * time;
						} else {
							System.out.println(
									String.format("Value %s for time limit is not valid.\nPlease use a valid integer.", s));
							String[] results = { String.format(
									"Value %s for time limit is not valid.\nPlease use a valid integer.", s) };
							return results;
						}
					} else {
						Matcher readyQueueMatcher = readyQueuePattern.matcher(s);
						if (readyQueueMatcher.matches()) {
							i++;
							s = args[i];
							Matcher numMatcher = numPattern.matcher(s);
							if (numMatcher.matches()) {
								System.out.println(String.format("Setting Ready Queue Size to %s.", s));
								readyQueueSize = Integer.parseInt(s);
							} else {
								System.out.println(String.format("Bad argument %s for option -q. Using default %d", s,
										readyQueueSize));
								String[] results = { String.format(
										"Value %s for Ready Queue size is not valid.\nPlease use a valid integer.",
										s) };
								return results;
							}
						} else {
							Matcher cpuMatcher = cpuPattern.matcher(s);
							if (cpuMatcher.matches()) {
								i++;
								s = args[i];
								Matcher numMatcher = numPattern.matcher(s);
								if (numMatcher.matches()) {
									System.out.println(String.format("Setting CPU Quantum to %s.", s));
									cpuQuantum = Integer.parseInt(s);
								} else {
									System.out.println(String.format("Bad argument %s for option -c. Using default %d",
											s, cpuQuantum));
									String[] results = { String.format(
											"Value %s for CPU Quantum is not valid.\nPlease use a valid integer.", s) };
									return results;
								}
							} else {
								Matcher limitMatcher = limitPattern.matcher(s);
								if (limitMatcher.matches()) {
									i++;
									s = args[i];
									Matcher numMatcher = numPattern.matcher(s);
									if (numMatcher.matches()) {
										System.out.println(String.format("Setting Limit on Iterations to %s.", s));
										iterationLimit = Integer.parseInt(s);
									} else {
										System.out.println(String.format(
												"Bad argument %s for option -l. Using default %d", s, iterationLimit));
										String[] results = { String.format(
												"Value %s for Iteration Limit is not valid.\\nPlease use a valid integer.",
												s) };
										return results;
									}
								} else {
									Matcher delayMatcher = delayPattern.matcher(s);
									if (delayMatcher.matches()) {
										i++;
										s = args[i];
										Matcher numMatcher = numPattern.matcher(s);
										if (numMatcher.matches()) {
											System.out.println(String.format("Setting Delay on Iterations to %s.", s));
											iterationDelay = Integer.parseInt(s);
										} else {
											System.out.println(
													String.format("Bad argument %s for option -d. Using default %d", s,
															iterationLimit));
											String[] results = { String.format(
													"Value %s for Iteration Delay is not valid.\\nPlease use a valid integer.",
													s) };
											return results;
										}
									}
								}
							}
						}

					}
				}
			}
			System.out.println("Using HyperParameter Values:");
		} else {
			System.out.println("No Command Line Args Found. Using Default HyperParameter Values:");
		}
		/* End - Process command line args */
		System.out.println(
				String.format("\ttime: %d\n\treadyQueueSize: %d\n\tcpuQuantum: %d\n\titerationLimit: %d\n\tIteration Delay: %d\n",
						time, readyQueueSize, cpuQuantum, iterationLimit, iterationDelay));
		System.out.println(
				"---------------------------------------------------------------------------------------------------------");
		System.out.println("------------------------------------ Starting Main Simulation # " + execution
				+ " -------------------------------------------");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------\n");
		ProcessGenerator generator = new ProcessGenerator();
		ReadyQueue readyQueue = new ReadyQueue(readyQueueSize);
		ArrayList<Process> completedProcesses = new ArrayList<Process>();
		CPU myCPU = new CPU(cpuQuantum);
		String startIterationString = "---------------------------------------------------------------------------------------------------------------\n------------------------------------ Execution %d Starting Iteration %d @ time %.2f -------------------------------------------";
		//String endIterationString = "------------------------------------ Execution # %d Finished Iteration %d @ time %d -------------------------------------------\n---------------------------------------------------------------------------------------------------------------";
		
		
		
		// Counter for units of time
		double timeUnits = 0;
		int execTime = 0;
		int count = 0;
		int executionCycles = 0;
		// Run until all processes have been generated
		//while (generator.hasMore() || readyQueue.isNotEmpty()) {
		//long startTime = System.nanoTime();
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - startTime < time){
			System.out.println(String.format(startIterationString, execution, count, timeUnits));
			while (readyQueue.isNotFull() && generator.hasMore()) {
				Process newProcess = generator.generateProcess();
				newProcess.setStartTime(timeUnits);
				System.out.println("MAIN: Adding process" + newProcess.toString());
				readyQueue.addProcess(newProcess);
				cycles++; // Adding process to Ready Queue adds one cycle
				timeUnits += .25; // Adding process to Ready Queue takes 1 time unit
			}
			System.out.println("MAIN: Processes on queue: " + readyQueue.size());

			if (readyQueue.isNotEmpty()) {
				Process lastProcess = myCPU.loadProcess(readyQueue.getNextProcess());
				cycles++; // Loading process on cpu adds one cycle
				if (lastProcess != null && lastProcess.execUnitsLeft() > 0) {
					System.out.println(String.format("MAIN: Process %d has %d more units putting back on queue.",
							lastProcess.getPid(), lastProcess.execUnitsLeft()));
					readyQueue.addProcess(lastProcess);
					cycles++; // Adding process to Ready Queue adds one cycle
					timeUnits += .25; // Adding process to Ready Queue adds .25 time units
				}else if (lastProcess != null && lastProcess.execUnitsLeft() <= 0) {
					System.out.println(String.format("MAIN: Process %d finished at %.2f", lastProcess.getPid(), timeUnits));
					lastProcess.setFinshTime(timeUnits);
					completedProcesses.add(lastProcess);
				}
				execTime = myCPU.executeCPU();
				timeUnits += execTime * .25;
				executionCycles += execTime;
				cycles = cycles + execTime;
				//Process unloadProcess = myCPU.unloadProcess();
				// cycles++; // Unloading process from cpu adds one cycle
				/*for (int i = 0; i < readyQueue.size(); i++) {
					Process tempProcess = readyQueue.get(i);
					tempProcess.delay(execTime * .25);
					readyQueue.set(i, tempProcess);
				}
				/*if (unloadProcess.execUnitsLeft() > 0) {
					System.out.println(String.format("MAIN: Process %d has %d more units putting back on queue.",
							unloadProcess.getPid(), unloadProcess.execUnitsLeft()));
					readyQueue.addProcess(unloadProcess);
					cycles++; // Adding process to Ready Queue adds one cycle
					timeUnits += .25; // Adding process to Ready Queue adds .25 time units
				} else {
					System.out.println(
							String.format("MAIN: Process %d finished at %.2f", unloadProcess.getPid(), timeUnits));
					unloadProcess.setFinshTime(timeUnits);
					completedProcesses.add(unloadProcess);
				}
				*/
			}
			
			
			//System.out.println("MAIN: Ran for " + execTime);
			//System.out.println("MAIN: Processes on queue now: " + readyQueue.size());
			// System.out.println("MAIN: ReadyQueue: " + readyQueue.toString());
			//System.out.println("MAIN: Generator has more: " + generator.hasMore());
			//System.out.println("MAIN: TIME:  " + timeUnits);
			//System.out.println(String.format("MAIN: CPU utilization: %.2f%%", utilization));
			//System.out.println("MAIN: Throughput: " + throughput +" processes per second.");
			//System.out.println(String.format(endIterationString, execution, count, timeUnits));
			//System.out.println("\n");
			count++;

			if (count > iterationLimit && iterationLimit != -1) {
				break;
			}

			try {
				Thread.sleep(iterationDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		long finishTime = System.currentTimeMillis();
		long totalTimeMs = ( finishTime - startTime);
		double totalTimeSeconds = totalTimeMs / 1000;
		System.out.println(
				"---------------------------------------------------------------------------------------------------------");
		System.out.println("------------------------------------ Execution # " + execution
				+ " Finished Main Simulation -------------------------------------------");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------\n");
		System.out.println(String.format("COMPLETE: Ran for %.2f seconds.", totalTimeSeconds));
		System.out.println(String.format("Time units executed: %.2f", timeUnits));
		
		int counterAll = 0;
		int counterComplete = 0;
		int counterIncomplete = 0;
		double delayTotal = 0.0;
		double executionTimeTotal = 0.0;
		double throughput_counter = 0.0;
		int executedUnitsTotal = 0;
		System.out.println("Completed Processes:");
		for (int i = 0; i < completedProcesses.size(); i++) {
			Process completedProcess = completedProcesses.get(i);
			System.out.println("\t" + completedProcess.toString());
			counterComplete++;
			delayTotal = delayTotal + completedProcess.getDelayTime();
			executionTimeTotal = executionTimeTotal + completedProcess.getTimeExecuted();
			executedUnitsTotal = executedUnitsTotal + completedProcess.getUnitsExecuted();
			throughput_counter++;
		}
		System.out.println("");
		System.out.println("Incomplete Processes:");
		while (readyQueue.isNotEmpty()) {
			Process incompleteProcess = readyQueue.getNextProcess();
			incompleteProcess.setFinshTime(timeUnits);
			System.out.println("\t" + incompleteProcess.toString());
			counterIncomplete++;
			delayTotal = delayTotal + incompleteProcess.getDelayTime();
			executionTimeTotal = executionTimeTotal + incompleteProcess.getTimeExecuted();
			throughput_counter += incompleteProcess.getUnitsExecuted() / incompleteProcess.getExecutionUnits();
		}
		System.out.println(String.format("\nExecution %d Summary:", execution));
		counterAll = counterComplete + counterIncomplete;
		System.out.println(String.format(
				"\t%d processes were generated.\n\tIn %.2f seconds %d completed and %d did not complete ",
				counterAll, totalTimeSeconds, counterComplete, counterIncomplete));
		System.out.println("Delay units: " + delayTotal);
		double tempCounterAll = counterAll;
		double avgDelay = delayTotal / tempCounterAll;
		double throughput = throughput_counter / (timeUnits ); // Divide by 
		double utilization = (executionCycles / cycles) * 100;
		System.out.println(String.format("\tThe total units executed was %.10f units", executionTimeTotal));
		System.out.println(String.format("\tThe Ready Queue was size %d processes", readyQueueSize));
		System.out.println(String.format("\tThe CPU quantum was %d units", cpuQuantum));
		System.out.println(String.format("\tTotal CPU Cycles: %.0f ", cycles));
		System.out.println(String.format("\tThe average delay was %f units", avgDelay));
		System.out.println(
				String.format("\tThe throughput in terms of processes was %f processes per unit of time", throughput));
		System.out.println(String.format("\tCPU utilization: %f%%", utilization));

		String returnString = new String(String.format("The total units executed was %.2f units", executionTimeTotal));
		returnString += String.format("\nThe Ready Queue was size %d units", readyQueueSize);
		returnString += String.format("\nThe CPU quantum was %d units", cpuQuantum);
		returnString += String.format("\nTotal CPU Cycles: %.0f ", cycles);
		returnString += String.format("\nThe average delay was %.2f units", avgDelay);
		returnString += String.format("\nThe throughput was %.2f processes per unit of time", throughput);
		returnString += String.format("\nCPU utilization: %.2f%%", utilization);

		// { "Execution", "Quantum", "Queue", "Delay", "Throughput", "Utilization" }
		String[] results = { returnString, String.format("%d", counterAll), String.format("%d", cpuQuantum),
				String.format("%d", readyQueueSize), String.format("%.2f units", avgDelay), String.format("%.2f", throughput),
				String.format("%.2f%%", utilization) };

		return results;

	}
	
	public static void main(String[] args) {
		mainRun(args,1);
	}
	
}