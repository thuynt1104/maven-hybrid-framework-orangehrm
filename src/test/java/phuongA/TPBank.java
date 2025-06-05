package phuongA;

public class TPBank {
    public void showTvName(){

        //Phạm vi dùng biến public ngoài class nhưng trong 1 package
        FPTCoporation tvName = new FPTCoporation();
        tvName.tvName = "abc";

        tvName.setTvName();
        tvName.tvColor = "anb";
        tvName.setChannel();
    }
}

