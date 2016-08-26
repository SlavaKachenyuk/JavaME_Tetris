/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

/**
 *
 * @author vkachenyuk
 */
public class ActionResult {
    public static final ActionResult done = new ActionResult();
    public static final ActionResult fail = new ActionResult();
    
    public final boolean isDone() {
        return this == ActionResult.done;
    }
    public final boolean isFail() {
        return this == ActionResult.fail;
    }
}
