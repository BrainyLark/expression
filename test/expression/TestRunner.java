/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expression;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author archer
 */
public class TestRunner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ExpressionAddMethodTest.class);
        
        for(Failure failure: result.getFailures()) {
            System.out.println(failure.toString());
        }
        
        System.out.println("[Add method] : Тестлел амжилттай " + ((result.wasSuccessful()) ? "боллоо!" : "болсонгүй!"));
        
        result = JUnitCore.runClasses(ExpressionGCDMethodTest.class);
        
        for(Failure failure: result.getFailures()) {
            System.out.println(failure.toString());
        }
        
        System.out.println("[GCD method] : Тестлел амжилттай " + ((result.wasSuccessful()) ? "боллоо!" : "болсонгүй!"));
    }
    
}
