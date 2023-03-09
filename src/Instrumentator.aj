import java.lang.Thread;
import java.io.*;
import java.util.*;
import java.text.*;

public aspect Instrumentator {

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	private int n = 0;

	pointcut main() : execution(* StudentApp.main(..));

	before(): main()
	{
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}

		try {
			PrintWriter out = new PrintWriter(
					new FileWriter("call-graph.dot", true));
			out.println("/*\n" + " * Description: This is an automatically generated .DOT file\n"
					+ "* representing a call graph.\n" + " * Author: Aryan Saxena, Ananya Varsha \n" + " * Date: "
					+ date + "\n" + " *\n" + " */\n" + "digraph call_graph {\n" + " node [shape = oval];");
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	pointcut database() : call(* connectdb.createtable(..)) || call(* FetchStudents.fetchdata(..)) || call(* connectdb.insertintotables(..));

	before() : database()
	{
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}

		try {
			PrintWriter out = new PrintWriter(
					new FileWriter("call-graph.dot", true));
			out.println(" " + thisJoinPoint.getTarget().getClass().getName() + " -> " + "[label = \"" + ++n + ". "
					+ thisJoinPoint.getSignature().getName() + "\"];");
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	pointcut updatedb() : call(* UpdateDetails.update(..));

	before(): updatedb(){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}

		try {
			PrintWriter out = new PrintWriter(
					new FileWriter("call-graph.dot", true));
			out.println(" " + thisJoinPoint.getThis().getClass().getName() + " -> " + "[label = \"" + ++n + ". "
					+ thisJoinPoint.getSignature().getName() + "\"];");
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	pointcut end() : call(* StudentApp.Quit(..));

	before(): end()
	{
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}
		try {
			PrintWriter out = new PrintWriter(
					new FileWriter("call-graph.dot", true));
			out.println("}");
			out.close();
		} catch (IOException e) {
		}
	}

}