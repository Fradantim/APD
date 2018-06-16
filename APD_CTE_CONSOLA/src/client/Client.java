package client;

import java.lang.reflect.InvocationTargetException;

public class Client {

	public static void main (String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		Runner runner= new Runner();
//		runner.NOexecute();
	
 	RunnerRMI runner= new RunnerRMI();
 	runner.NOexecute();

}}
