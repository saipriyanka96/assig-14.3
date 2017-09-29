package test;
//Package is a grouping of related types providing access protection and name 
//test is the package name
import java.util.logging.Level;
//level class defines a set of standard logging levels that can be used to control 
//logging output
//Logger object is used to log message for specific system or application component
import java.util.logging.Logger;

public class NotificationTest {
//public keyword is used in the declaration of a class,method or 
//field;public classes,method and fields can be accessed by the 
//members of any class.
////class defines instance and class fields,methods and inner classes as
//well as specifying the interfaces the class implements and the 
//immediate superclass of the class

	private volatile boolean go=false;
//we volatile to make class thread safe
	public static void main(String[] args) throws InterruptedException {
		//static is used for memory management
//String[] args is actually an array of java.lang.String type and it's name is args here
//InterruptedException is thrown when a thread is waiting,sleeping or occupied 
//and the thread is interrupted,either before or during the activity
		final NotificationTest test=new NotificationTest();
//final keyword with variables to specify its values are not to be changed		
		Runnable waitTask=new Runnable(){
//The runnable interface should be implemented by any class whose instance are intended 
//to be executed by a thread
			@Override
			public void run()
////void is used to define the return type of the method
//public void run-if this thread object was instantiated using a runnable 
//target, the run() method is invoked on that runnable
	
			{
/*the code is prone to exceptions is placed in the try block
*when exception occurs that exception occurred is handled by the 
*catch block associated with it
*/
				try
				{
					test.shouldGo();
				}
				catch(InterruptedException e)
				{
					Logger.getLogger(NotificationTest.class.getName());
			//getting the name from class file
					log(Level.SEVERE,null,e);
	//SEVERE is a message level indicating a serious failure. 
				}
				System.out.println(Thread.currentThread()+"fininshed Execution");
				//system is final class
				//out is a static member of system class and type printStream
				//println is method of printStream class.ln means nextline
				
			}

			private void log(Level severe, Object object, InterruptedException e) {
				// TODO Auto-generated method stub
				
			}
		};
		Runnable notifyTask=new Runnable()
		//creating an object
		//for notifying task 
		{
			@Override
			public void run()
			{
				try {
					test.shouldGo();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				//catching the exception
				}
				System.out.println(Thread.currentThread()+"fininshed Execution");
			}
		};
		Thread t1=new Thread(waitTask,"WT1");//wait
		//creating new thread with a message
		Thread t2=new Thread(waitTask,"WT2");//wait
		Thread t3=new Thread(waitTask,"WT3");//wait
		Thread t4=new Thread(waitTask,"NT1");//notify
		//starting all waiting thread
		t1.start();
		t2.start();
		t3.start();
	//staring the threads to print and then thread will sleep for 200 milliseconds
	//then it will start thread 4	
	//pausing to ensure all waiting thread started successfully
		Thread.sleep(200);
		t4.start();
	}
	private synchronized void shouldGo() throws InterruptedException
	//Synchronized is the capability to contrlo the access of multiple thread to any shared resourcr
	//private means is only accessible within the same class as it is declared
	//shouldGo is a method
	//throws is an exception
	{
		while(go!=true)
		{
			System.out.println(Thread.currentThread()+"is going to wait on this object");
			wait();//is from object and used to wait 
			System.out.println(Thread.currentThread()+"is woke up");
		}
		go=false;//if false then it will print nothing
	}
	private synchronized void go()
	{
		while(go==false)//resetting the keyword
//while statement evaluates expression which must return a boolean value
		{
			System.out.println(Thread.currentThread()+"is going to notify all or one thread waiting on this object");
			go=true;//making condition true for waiting thread
			notify();//wakes only one thread
//only one of the three thread will wake up
			notifyAll();//wakes all threads
	//all threads will take
//there are two methods notify and notify all for waking threads waiting on some condition
//notify only one of waiting for thread will be woken and it's not guaranteed which thread will be woken it depends on upon Thread scheduler
//notifyAll means waiting on that lock will be woken up,but again all woken thread will fight for lock before executing remaining code that's 
//why wait is called loop because if multiple threads are woken up the thread which will get lock will first execute and it may rest waiting 
//for condition which will force subsequent threads to wait
		}
	}
}
