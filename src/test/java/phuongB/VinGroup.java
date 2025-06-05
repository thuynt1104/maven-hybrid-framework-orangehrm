package phuongB;

import phuongA.FPTCoporation;
//ngoai class nhưng khác package
public class VinGroup {
    public void showTvName(){

        //Phạm vi dùng biến public ngoài class nhưng trong 1 package
        FPTCoporation tvName = new FPTCoporation();
        tvName.tvName = "abc";

        tvName.setTvName();
    }
}
