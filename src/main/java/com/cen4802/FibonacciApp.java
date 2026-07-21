package com.cen4802;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class FibonacciApp {
    private static final Logger logger = Logger.getLogger(FibonacciApp.class.getName());

    /**
     * Runs the Fibonacci application and generates log entries for the logging assignment.
     * The method writes log messages to both the console and a log file while still
     * displaying the final Fibonacci result.
     *
     * @param args command-line arguments, not used in this application
     */

    public static void main(String[] args) {
        configureLogging();

        if (args.length > 0 && args[0].equalsIgnoreCase("profile")) {
            runProfilingMode();
            shutdownLogging();
            return;
        }

        logger.info("Fibonacci application started.");

        for (int i = 1; i <= 100; i++) {
            int term = 10;
            int result = fibonacci(term);

            logger.info("Iteration " + i + ": the " + term
                    + "th term of the Fibonacci sequence is " + result + ".");

            if (i % 20 == 0) {
                logger.warning("Checkpoint reached at iteration " + i
                        + ". Application is still calculating Fibonacci values successfully.");
            }
        }

        int invalidTerm = -1;

        if (invalidTerm < 0) {
            logger.severe("Intentional logging demonstration: invalid Fibonacci input was detected and handled. "
                    + "Invalid term: " + invalidTerm);
        }

        int term = 10;
        int result = fibonacci(term);

        logger.info("Final workflow demo, the " + term
                + "th term of the Fibonacci sequence is " + result + ".");

        System.out.println("Final workflow demo, the 10th term of the Fibonacci sequence is " + result + ".");

        logger.info("Fibonacci application finished.");

        shutdownLogging();
    }

    /**
     * Calculates the nth term in the Fibonacci sequence using recursion.
     *
     * @param n the position in the Fibonacci sequence
     * @return the Fibonacci value at the specified position
     */

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * Runs a repeated Fibonacci workload so VisualVM can collect profiling data.
     * This mode keeps the application active long enough to observe CPU, memory,
     * and thread behavior during execution.
     */

    private static void runProfilingMode() {
        logger.info("Profiling mode started. Application will keep running for VisualVM.");

        int iteration = 1;

        while (true) {
            long startTime = System.nanoTime();

            int result = 0;

            for (int i = 0; i < 5; i++) {
                result = fibonacci(35);
            }

            long elapsedTime = (System.nanoTime() - startTime) / 1_000_000;

            logger.info("Profiling iteration " + iteration
                    + " completed. Fibonacci(35) = " + result
                    + ". Execution time: " + elapsedTime + " ms.");

            iteration++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                logger.warning("Profiling mode was interrupted.");
                break;
            }
        }
    }

    /**
     * Configures the application's logging behavior.
     * This method creates the logs folder, disables the default parent handlers,
     * and adds both a console handler and a file handler.
     */

    private static void configureLogging() {
        try {
            Files.createDirectories(Path.of("logs"));

            logger.setUseParentHandlers(false);
            logger.setLevel(Level.ALL);

            for (Handler handler : logger.getHandlers()) {
                logger.removeHandler(handler);
            }

            Formatter formatter = new OneLineFormatter();

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            consoleHandler.setFormatter(formatter);

            FileHandler fileHandler = new FileHandler("logs/fibonacci-app.log", false);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(formatter);

            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);

        } catch (IOException exception) {
            throw new RuntimeException("Unable to configure logging.", exception);
        }
    }

    /**
     * Flushes and closes all logging handlers used by the application.
     * This helps make sure all log entries are written before the program exits.
     */

    private static void shutdownLogging() {
        for (Handler handler : logger.getHandlers()) {
            handler.flush();
            handler.close();
        }
    }


    /**
     * Custom formatter used to make each log entry easier to read.
     * Each log entry includes a timestamp, severity level, logger name, and message.
     */

    private static class OneLineFormatter extends Formatter {

        /**
         * Formats a log record into a single human-readable line.
         *
         * @param record the log record created by the logger
         * @return the formatted log message
         */

        @Override
        public String format(LogRecord record) {
            String message = String.format(
                    "%1$tF %1$tT %2$-7s %3$s - %4$s%n",
                    Date.from(record.getInstant()),
                    record.getLevel().getName(),
                    record.getLoggerName(),
                    formatMessage(record)
            );

            if (record.getThrown() != null) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                record.getThrown().printStackTrace(printWriter);
                message += stringWriter;
            }

            return message;
        }
    }

}