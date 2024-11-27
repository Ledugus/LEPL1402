package oop;

import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

/**
 * Your task is to control a robot using a sequence of textual
 * commands. The robot can move forward, turn left, or turn right. The
 * robot is controlled through the following set of 4 basic commands:
 * <p>
 * - FORWARD
 * => Move the robot forward
 * <p>
 * - LEFT
 * => Turn the robot to the left
 * <p>
 * - RIGHT
 * => Turn the robot to the right
 * <p>
 * - REPEAT N
 * ...
 * END REPEAT
 * => Repeat "N" times the commands "..."
 * <p>
 * For instance, here is a sample sequence of textual commands:
 * <p>
 * FORWARD
 * REPEAT 3
 * FORWARD
 * RIGHT
 * END REPEAT
 * FORWARD
 * FORWARD
 * <p>
 * If applied to a robot that turns at right angles, this sample
 * sequence would generate the following pattern, where "x" denotes
 * the starting point of the robot, and "^" is its final position:
 * <p>
 * ^
 * |
 * |
 * x---->---->
 * |    |
 * |    |
 * <----<
 * <p>
 * Pay attention to the fact that the "REPEAT" commands can be nested
 * (i.e. a "REPEAT" command may recursively contain other "REPEAT"
 * commands).
 * <p>
 * Using the "Factory" design pattern, you must convert an array of
 * strings containing commands into an "Action" object that can be
 * used to control one instance of the "Robot" interface.
 **/

public class RobotActionFactory {

    /**
     * The factory method you have to implement.
     * <p>
     * NB 1: In order to parse an integer from some string "s", you
     * can use the standard function "Integer.parseInt(s)".
     * <p>
     * NB 2: If the array of commands cannot be parsed (e.g. because
     * of an unknown action, or because of a "REPEAT" command without
     * an "END REPEAT"), you must throw an exception of class
     * "IllegalArgumentException".
     *
     * @param commands The array of commands to drive the robot.
     * @return An "Action" object that will move the robot
     * according to the commands.
     **/
    public Action parse(String[] commands) {
        SequenceOfActions sequence = new SequenceOfActions();
        int i = 0;
        while (i < commands.length) {

            if (commands[i].equals("FORWARD")) {
                sequence.add(new MoveForwardAction());
                i++;
            } else if (commands[i].equals("RIGHT")) {
                sequence.add(new TurnRightAction());
                i++;
            } else if (commands[i].equals("LEFT")) {
                sequence.add(new TurnLeftAction());
                i++;
            } else if (commands[i].startsWith("REPEAT")) {
                String[] command = commands[i].split(" ");
                int times = Integer.parseInt(command[1]);

                int depth = 1;
                int j = i + 1;

                while (depth > 0 &&
                        j < commands.length) {
                    if (commands[j].startsWith("REPEAT")) {
                        depth ++;
                    } else if (commands[j].equals("END REPEAT")) {
                        depth --;
                    }
                    j++;
                }

                if (depth == 0) {
                    Action action = parse(Arrays.copyOfRange(commands, i + 1, j - 1));
                    sequence.add(new RepeatAction(times, action));
                    i = j;
                } else {
                    throw new IllegalArgumentException("Missing END REPEAT");
                }
            } else {
                throw new IllegalArgumentException("Unknown command: " + commands[i]);
            }
        }
        return sequence;
    }


    /**
     * Interface defining an abstract robot to be controlled.
     **/
    public static interface Robot {

        /**
         * Move the robot forward.
         **/
        void moveForward();

        /**
         * Turn the robot to the left.
         **/
        void turnLeft();

        /**
         * Turn the robot to the right.
         **/
        void turnRight();
    }


    /**
     * Interface defining an abstract action that modifies the state
     * of the given robot. An action can correspond to one isolated
     * command (move forward, turn left, or turn right), to one
     * sequence of actions, or to one repetition of an action.
     **/
    public static interface Action {

        /**
         * Apply this action to the given robot.
         *
         * @param robot The robot.
         **/
        void apply(Robot robot);
    }

    /**
     * This type of "Action" moves the robot forward.
     **/
    private static class MoveForwardAction implements Action {
        @Override
        public void apply(Robot robot) {
            robot.moveForward();
        }
    }

    /**
     * This type of "Action" turns the robot to the left.
     **/
    private static class TurnLeftAction implements Action {
        @Override
        public void apply(Robot robot) {
            robot.turnLeft();
        }
    }

    /**
     * This type of "Action" turns the robot to the right.
     **/
    private static class TurnRightAction implements Action {
        @Override
        public void apply(Robot robot) {
            robot.turnRight();
        }
    }

    /**
     * This type of "Action" represents a sequence of actions to be
     * applied to the robot.
     **/
    private static class SequenceOfActions implements Action {
        private List<Action> actions = new LinkedList<Action>();

        /**
         * Append a new action to the end of the sequence of actions.
         *
         * @param action The action to be added.
         **/
        public void add(Action action) {
            actions.add(action);
        }

        @Override
        public void apply(Robot robot) {
            for (Action a : actions) {
                a.apply(robot);
            }
        }
    }

    /**
     * This type of "Action" executes another action, for a given
     * number of times.
     **/
    private static class RepeatAction implements Action {
        private int times;
        private Action action;

        /**
         * Constructor for a repetition of one action.
         *
         * @param times  The number of times the action must be executed.
         * @param action The action to be repeated.
         **/
        RepeatAction(int times,
                     Action action) {
            this.times = times;
            this.action = action;
        }

        @Override
        public void apply(Robot robot) {
            for (int i = 0; i < times; i++) {
                action.apply(robot);
            }
        }
    }


}
