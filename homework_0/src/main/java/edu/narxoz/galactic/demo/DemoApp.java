package edu.narxoz.galactic.demo;

import edu.narxoz.galactic.bodies.*;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.dispatcher.Dispatcher;
import edu.narxoz.galactic.dispatcher.Result;
import edu.narxoz.galactic.drones.*;
import edu.narxoz.galactic.task.DeliveryTask;

public class DemoApp {
    public static void main(String[] args) {

        Planet earth = new Planet("Earth", 0, 0, "Oxygen");
        SpaceStation marsOrbital = new SpaceStation("Mars Orbital", 100, 0, 3);

        Cargo heavyCargo = new Cargo(50, "Mining Equipment");

        LightDrone light = new LightDrone("LD-1", 20);
        HeavyDrone heavy = new HeavyDrone("HD-1", 100);

        DeliveryTask task = new DeliveryTask(earth, marsOrbital, heavyCargo);
        Dispatcher dispatcher = new Dispatcher();

        Result r1 = dispatcher.assignTask(task, light);
        System.out.println("Assign to LightDrone: " + r1);

        Result r2 = dispatcher.assignTask(task, heavy);
        System.out.println("Assign to HeavyDrone: " + r2);

        double time = task.estimateTime();
        System.out.println("Estimated time: " + time + " minutes");

        Result r3 = dispatcher.completeTask(task);
        System.out.println("Completion result: " + r3);

        System.out.println("Final drone status: " + heavy.getStatus());
        System.out.println("Final task state: " + task.getState());
    }
}
