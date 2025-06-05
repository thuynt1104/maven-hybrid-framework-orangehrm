package phuongB;

import phuongA.FPTCoporation;

public class FPTSoftware extends FPTCoporation {
    public void showTvName(){

        //Phạm vi dùng biến public ngoài class nhưng trong 1 package
        FPTCoporation tvName = new FPTCoporation();
        tvName.tvName = "abc";
        tvColor = "abc";
    }
}
