package com.segi.uhomecp.ice.service;

import java.util.Map;

import com.segi.uhomecp.init.MedicaltransBaseIceBoxService;

public class ICEBoxServer {
	
	//修改端口
	private static final int PORT = 6301;
	
	private static final String INIT_ARGS = "--Ice.Default.Locator=IceGrid/Locator:tcp -h localhost -p " + PORT;
	public static void main(String[] args) {
		args = new String[] {INIT_ARGS};
		startServiceByRegCenter(PORT,args);
	}
	
	public static void startServiceByLocal(Ice.Object object,String servcieName,String port){
		int status = 0;
		// Communicator实例，是ice run time的主句柄
		Ice.Communicator ic = null;
		try {
			// 调用Ice.Util.Initialize()初始化Ice run time
			System.err.println("初始化  ice run time...");
			
			// 调用Ice.Util.Initialize()初始化Ice run time
			String[] args = new String[]{} ;
			ic = Ice.Util.initialize(args); // args参数可传可不传

			// 创建一个对象适配器，传入适配器名字和在10000端口处接收来的请求
			System.out.println("创建对象适配器，监听端口 "+port+" 。。。。。。");
			Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints(
					"SimplePrinterAdapter", "default -p " + port);

			// 实例化一个PrinterI对象，为Printer接口创建一个servant
//			System.out.println("为接口创建servant...");
//			Ice.Object object = new HelloWorld();

			// 调用适配器的add,告诉它有一个新的servant,传递的参数是刚才的servant,这里的servcieName是Servant的名字
			System.out.println("对象适配器加入servant... interface: "+servcieName);
			adapter.add(object, Ice.Util.stringToIdentity(servcieName));

			// 调用适配器的activate()方法，激活适配器。被激活后，服务器开始处理来自客户的请求。
			adapter.activate();
			System.err.println("ICE服务端已经准备完毕");

			// 这个方法挂起发出调用的线程，直到服务器实现终止为止。或我们自己发出一个调用关闭。
			ic.waitForShutdown();
		} catch (Ice.LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			e.printStackTrace();
			status = 1;
		} finally {
			if (ic != null) {
				ic.destroy();
			}
		}
		System.exit(status);
	}

	// 启动服务
	public static void startServiceByRegCenter(int port,String[] args) {
		int status = 0;
		// Communicator实例，是ice run time的主句柄
		Ice.Communicator ic = null;
		try {
			System.err.println("初始化  ice run time...");
			// 调用Ice.Util.Initialize()初始化Ice run time
			//args = new String[] { "--Ice.Default.Locator=IceGrid/Locator:tcp -h localhost -p 4062" };
			ic = Ice.Util.initialize(args);


			System.out.println("创建对象适配器，监听端口 "+port+" 。。。。。。");
			// 创建一个对象适配器，传入适配器名字和在8756端口处接收来的请求
			Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints(
					"SimplePrinterAdapter", "default -p " + port);

			MedicaltransBaseIceBoxService def = new MedicaltransBaseIceBoxService();

			for (Map.Entry<String, Ice.Object> entry : def.createIceServiceObj(args).entrySet()) {
				// 调用适配器的add,告诉它有一个新的servant,传递的参数是刚才的servant,这里的“SimplePrinter”是Servant的名字
				System.out.println("---" + entry.getKey());
				adapter.add(entry.getValue(),Ice.Util.stringToIdentity(entry.getKey()));
			}
			
			// 调用适配器的activate()方法，激活适配器。被激活后，服务器开始处理来自客户的请求。
			adapter.activate();


			System.err.println("ICE服务端已经准备完毕");
			
			// 这个方法挂起发出调用的线程，直到服务器实现终止为止。或我们自己发出一个调用关闭。
			ic.waitForShutdown();
		} catch (Ice.LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			e.printStackTrace();
			status = 1;
		} finally {
			if (ic != null) {
				ic.destroy();
			}
		}
		System.exit(status);
	}
}
