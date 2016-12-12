package amphetamin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/**
 *
 * @author dmusiolik
 */
public class backbone {
    
    public void playSound() {
    try {
        this.alert_err(Amphetamin.media_dir + Amphetamin.jList1.getSelectedValue(), "alert");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Amphetamin.media_dir + Amphetamin.jList1.getSelectedValue()).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(Exception ex) {
        System.out.println("Error with playing sound.");
        ex.printStackTrace();
    }
}

    
    //ERROR_MESSAGE
    public void alert_err(String content, String kopf) {
        JOptionPane.showMessageDialog(null, content, kopf, JOptionPane.ERROR_MESSAGE);
    }

    //INFORMATION_MESSAGE
    public void alert_info(String content, String kopf) {
        JOptionPane.showMessageDialog(null, content, kopf, JOptionPane.INFORMATION_MESSAGE);
    }
    
    //Check status of env
    public boolean env_check() {
        //create dir
        File f = new File(".amphetamin");
        if (f.exists() && f.isDirectory()) {
            System.out.println("dir exists");
            return true;
        }else {
            f.mkdir();
            return false;
        }
    }
    
    public void create_default_conf() {
        this.write("mediadir", ".amphetamin/", "Music/");
    }
    
    public String read_mediadir_conf() {
        
        String feedback = "ERROR!";

        try {
            FileReader fr = new FileReader(".amphetamin/mediadir.conf");
            BufferedReader br = new BufferedReader(fr);
            feedback = br.readLine();
        } catch (Exception e) {
        }

        return feedback;
    }
    
    //Write
    public void write(String file, String localation, String content) {

        try {

            File file2write = new File(localation + file + ".conf");

            if (!file2write.exists()) {
                file2write.createNewFile();
            }

            FileWriter fw = new FileWriter(file2write.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Done -" + file + ".conf\t=> Output from write\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
