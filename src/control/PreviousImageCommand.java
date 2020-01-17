package control;

import view.ImageDisplay;

public class PreviousImageCommand implements Command{

    private final ImageDisplay imageDisplay;

    public PreviousImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }
    
    @Override
    public void execute() {
        this.imageDisplay.show(this.imageDisplay.current().prevImage());
    }
    
}
