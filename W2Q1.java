import java.io.File;

public class W2Q1 {
    public static void main(String[] args) {
        String folderPath = "D:\\高级程序设计";
        File directory = new File(folderPath);
        //调用printAllFiles方法打印文件夹下所有文件的绝对路径
        printAllFiles(directory);
    }
    /**
     * 打印文件夹下所有文件的绝对路径
     * @param directory 文件夹
     */
    public static void printAllFiles(File directory) {
        if(!directory.exists()){
            System.out.println("该文件地址不存在");
            return;
        }
        if(!directory.isDirectory()){
            System.out.println("文件:"+directory.getAbsolutePath());
            return;
        }
        File[] files = directory.listFiles();
        if(files==null){
            System.out.println("文件夹"+directory.getAbsolutePath()+"内容无法获取");
            return;
        }
        for(File file:files){
            if(file.isFile()){
                System.out.println("文件："+file.getAbsolutePath());
            }
            if(file.isDirectory()){
                System.out.println("进入文件夹"+file.getAbsolutePath()+"读取文件");
                //如果是文件夹，递归调用printAllFiles方法打印文件夹下所有文件的绝对路径
                printAllFiles(file);
            }
        }
    }
}
