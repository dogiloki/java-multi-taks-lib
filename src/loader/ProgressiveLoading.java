package loader;

import javax.swing.JProgressBar;

/**
 *
 * @author _dogi
 */

public class ProgressiveLoading{
    
    private int max=100;
    private int current=0;
    private int init=0;
    private int step=1;
    private JProgressBar progress;
    
    public ProgressiveLoading(JProgressBar progress){
        this.progress=progress;
    }
    
    
    
}
