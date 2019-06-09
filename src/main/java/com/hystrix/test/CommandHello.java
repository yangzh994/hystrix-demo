package com.hystrix.test;

import com.netflix.hystrix.*;

public class CommandHello extends HystrixCommand<String> {

    private String name;

    private volatile static int i = 5;

    public CommandHello(String name){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutEnabled(true).withExecutionTimeoutInMilliseconds(300).withCircuitBreakerEnabled(true).withCircuitBreakerRequestVolumeThreshold(2).withCircuitBreakerSleepWindowInMilliseconds(2000)));
        this.name = name;
    }

    protected String run() throws Exception {

      if(i > 0){
          System.out.println(Thread.currentThread().getName() + i);
          i--;
          Thread.sleep(1000);
      }
      return "hello" + name + "!";
    }

    public static void main(String[] args) throws InterruptedException {
        String result = new CommandHello("yang").execute();
        System.out.println(result);

        String result1 = new CommandHello("yang").execute();
        System.out.println(result1);

        String result2 = new CommandHello("yang").execute();
        System.out.println(result2);

        String result3 = new CommandHello("yang").execute();
        System.out.println(result3);

        String result4 = new CommandHello("yang").execute();
        System.out.println(result4);

        String result5 = new CommandHello("yang").execute();
        System.out.println(result5);

        String result6 = new CommandHello("yang").execute();
        System.out.println(result6);

        Thread.sleep(2000);
        String result7 = new CommandHello("yang").execute();
        System.out.println(result7);

        String result8 = new CommandHello("yang").execute();
        System.out.println(result8);

        String result9 = new CommandHello("yang").execute();
        System.out.println(result9);
        String result10 = new CommandHello("yang").execute();
        System.out.println(result10);
    }

    @Override
    protected String getFallback() {
        return "error";
    }
}
