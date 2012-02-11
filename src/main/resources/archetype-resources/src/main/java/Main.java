package $package;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import org.mortbay.jetty.Server;

import clojure.lang.RT;

public class Main {

	public static void main(String[] args) throws Exception {
		try {
			//load your top level script
			RT.loadResourceScript("my_website/server.clj");
			
			Callable<Server> server = new Callable<Server>() {
				public Server call() throws Exception {
					return (Server)RT.var("my-website.server", "start").invoke();
				}
			};
			
			FutureTask<Server>task = new FutureTask<Server>(server);
			
			Executor e = new Executor() {
				public void execute(Runnable r) {
					r.run();
				}
			};
			
			e.execute(task);
			task.get().join();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1); //let the OS know we failed
		}
		System.exit(0);
	}
}