import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public void extract(ZipFile zipFile) {
    String toDir = "/my/target/directory/";
    Enumeration<? extends ZipEntry> entries = zipFile.entries();
    while (entries.hasMoreElements()) {
        ZipEntry zipEntry = entries.nextElement();
        File file = new File(toDir, zipEntry.getName());
        try (InputStream istr = zipFile.getInputStream(zipEntry);
             OutputStream os = Files.newOutputStream(file.toPath())) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = istr.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
        } catch (IOException e) {
            // Handle exception
        }
    }
}
