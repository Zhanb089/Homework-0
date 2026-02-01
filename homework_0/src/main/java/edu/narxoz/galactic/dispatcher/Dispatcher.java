package edu.narxoz.galactic.dispatcher;

import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.DroneStatus;
import edu.narxoz.galactic.task.DeliveryTask;
import edu.narxoz.galactic.task.TaskState;

public class Dispatcher {
    public Result assignTask(DeliveryTask task, Drone drone) {
        if (task == null || drone == null) {
            return new Result(false, "Task or drone is null");
        }
        if (drone.getStatus() != DroneStatus.IDLE) {
            return new Result(false, "Drone not idle");
        }
        if (task.getState() != TaskState.CREATED) {
            return new Result(false, "Task not in CREATED state");
        }
        if (task.getCargo().getWeightKg() > drone.getMaxPayloadKg()) {
            return new Result(false, "Cargo too heavy");
        }

        task.setAssignedDrone(drone);
        task.setState(TaskState.ASSIGNED);
        drone.setStatus(DroneStatus.IN_FLIGHT);

        return new Result(true, "");
    }

    public Result completeTask(DeliveryTask task) {
        if (task == null) {
            return new Result(false, "Task is null");
        }
        if (task.getState() != TaskState.ASSIGNED) {
            return new Result(false, "Task not assigned");
        }
        if (task.getAssignedDrone() == null) {
            return new Result(false, "No drone assigned");
        }
        if (task.getAssignedDrone().getStatus() != DroneStatus.IN_FLIGHT) {
            return new Result(false, "Drone not in flight");
        }

        task.setState(TaskState.DONE);
        task.getAssignedDrone().setStatus(DroneStatus.IDLE);

        return new Result(true, "");
    }
}
