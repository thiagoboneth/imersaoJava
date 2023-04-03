import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {
    

    void cria(InputStream inputStream, String nomeArquivo, String nomeFilme) throws Exception{

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 50;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 10);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        graphics.drawString(nomeFilme, 3, novaAltura - 20);

        ImageIO.write(novaImagem, "png", new File("../src/img/"+nomeArquivo));
    }
}
