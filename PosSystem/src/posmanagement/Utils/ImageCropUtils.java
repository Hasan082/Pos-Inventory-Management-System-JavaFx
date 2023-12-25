
package posmanagement.Utils;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageCropUtils {

    public static void resizeAndCropImage(ImageView imageView, Image image, int width, int height) {
        imageView.setImage(resizeImage(image, width, height, true));

        double sourceRatio = imageView.getImage().getWidth() / imageView.getImage().getHeight();
        double targetRatio = (double) width / height;
        double x, y, w, h;

        if (sourceRatio > targetRatio) {
            h = height;
            w = h * sourceRatio;
            x = (w - width) / 2;
            y = 0;
        } else {
            w = width;
            h = w / sourceRatio;
            x = 0;
            y = (h - height) / 2;
        }

        imageView.setViewport(new Rectangle2D(x, y, w, h));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(false);
    }

    private static Image resizeImage(Image image, int width, int height, boolean max) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);

        if (max) {
            imageView.setSmooth(true);
        }

        return imageView.snapshot(null, null);
    }
}
