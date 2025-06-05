package phuongA;

public class FPTCoporation {
    //Thuoc tính

    //Tất cả nơi trong dự án này thì đều gọi đc tvName
    public String tvName;
    //
    protected String tvColor;
    //chỉ dùng trong package
    String tvChannel;
    //Chỉ class mới sử dụng được private
    private String tvVolumn;

    //Phương thức
    public void setTvName(){
        System.out.println(tvName);
    }

    //cùng package là dùng được và thông qua kế thừa
    protected void setTvColor(){
        System.out.println(tvColor);
    }

    void setChannel(){
        System.out.println(tvChannel);
    }

    private void setTvVolumn(){
        System.out.println(tvVolumn);
    }
}
