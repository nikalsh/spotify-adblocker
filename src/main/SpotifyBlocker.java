package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author nikalsh
 */
public class SpotifyBlocker {

    private static final String DELIMIT = "####SPOTIFYADBLOCKER####";

    private static String HOSTS_TO_BLOCK
            = String.format(DELIMIT + "%n"
                    + "0.0.0.0 adclick.g.doublecklick.net%n"
                    + "0.0.0.0 adeventtracker.spotify.com%n"
                    + "0.0.0.0 ads-fa.spotify.com%n"
                    + "0.0.0.0 analytics.spotify.com%n"
                    + "0.0.0.0 audio2.spotify.com%n"
                    + "0.0.0.0 b.scorecardresearch.com%n"
                    + "0.0.0.0 bounceexchange.com%n"
                    + "0.0.0.0 bs.serving-sys.com%n"
                    + "0.0.0.0 content.bitsontherun.com%n"
                    + "0.0.0.0 core.insightexpressai.com%n"
                    + "0.0.0.0 crashdump.spotify.com%n"
                    + "0.0.0.0 d2gi7ultltnc2u.cloudfront.net%n"
                    + "0.0.0.0 d3rt1990lpmkn.cloudfront.net%n"
                    + "0.0.0.0 desktop.spotify.com%n"
                    + "0.0.0.0 doubleclick.net%n"
                    + "0.0.0.0 ds.serving-sys.com%n"
                    + "0.0.0.0 googleadservices.com%n"
                    + "0.0.0.0 googleads.g.doubleclick.net%n"
                    + "0.0.0.0 gtssl2-ocsp.geotrust.com%n"
                    + "0.0.0.0 js.moatads.com%n"
                    + "0.0.0.0 log.spotify.com%n"
                    + "0.0.0.0 media-match.com%n"
                    + "0.0.0.0 omaze.com%n"
                    + "0.0.0.0 open.spotify.com%n"
                    + "0.0.0.0 pagead46.l.doubleclick.net%n"
                    + "0.0.0.0 pagead2.googlesyndication.com%n"
                    + "0.0.0.0 partner.googleadservices.com%n"
                    + "0.0.0.0 pubads.g.doubleclick.net%n"
                    + "0.0.0.0 redirector.gvt1.com%n"
                    + "0.0.0.0 s0.2mdn.net%n"
                    + "0.0.0.0 securepubads.g.doubleclick.net%n"
                    + "0.0.0.0 spclient.wg.spotify.com%n"
                    + "0.0.0.0 tpc.googlesyndication.com%n"
                    + "0.0.0.0 v.jwpcdn.com%n"
                    + "0.0.0.0 video-ad-stats.googlesyndication.com%n"
                    + "0.0.0.0 weblb-wg.gslb.spotify.com%n"
                    + "0.0.0.0 www.googleadservices.com%n"
                    + "0.0.0.0 www.googletagservices.com%n"
                    + "0.0.0.0 www.omaze.com%n"
                    + DELIMIT + "%n");

    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final String SYSROOT = System.getenv("SystemDrive");

    private static final String MAC = "/etc/hosts";
    private static final String GNULINUX = "/etc/hosts";
    private static final String WINDOWS = SYSROOT + "/Windows/System32/drivers/etc/hosts";
    private String HOSTS_PATH = (OS.contains("windows") ? WINDOWS : (OS.contains("mac") ? MAC : GNULINUX));
    private String blockOrUnblock;

    private static final String BLOCK = "block";
    private static final String UNBLOCK = "unblock";

    public SpotifyBlocker(String blockOrUnblock) throws FileNotFoundException, IOException {

        this.blockOrUnblock = blockOrUnblock;
        block();
    }

    public SpotifyBlocker(String blockOrUnblock, String customHostsPath) throws FileNotFoundException, IOException {

        this.blockOrUnblock = blockOrUnblock;
        this.HOSTS_PATH = customHostsPath;
        block();

    }

    public void block() throws FileNotFoundException, IOException {
        System.out.println(OS);
        System.out.println(SYSROOT);
        File file = new File(HOSTS_PATH);
        String in = "";

        BufferedReader fr = new BufferedReader(new FileReader(file));

        SW:
        switch (blockOrUnblock.toLowerCase()) {

            case "block":
                while ((in = fr.readLine()) != null) {

                    if (in.matches(DELIMIT)) {
                        System.out.format("Already blocking%nExiting..");
                        fr.close();
                        break SW;
                    }
                }

                FileWriter fw = new FileWriter(file, true);
                fw.append(HOSTS_TO_BLOCK);
                fw.close();
                break;

            case "unblock":
                File tempFile = new File(HOSTS_PATH + "temp");
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile, true));

                OUTER:
                while ((in = fr.readLine()) != null) {

                    if (in.matches(DELIMIT)) {

                        while (true) {
                            fr.readLine();

                            if (in.matches(DELIMIT)) {
                                break OUTER;
                            }
                        }
                    }
                    System.out.println(in);
                    pw.println(in);
                    pw.flush();

                }
                pw.close();
                fr.close();

                file.delete();
                tempFile.renameTo(new File(HOSTS_PATH));

                break;

            default:
                System.out.format("Invalid argument: %s%nExiting..%n", blockOrUnblock);
                break;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        if (args.length == 2) {
            //Block argument + custom path argument
            SpotifyBlocker sb = new SpotifyBlocker(args[0], args[1]);

        } else {
            SpotifyBlocker sb = new SpotifyBlocker((args.length > 1 ? args[0] : "null"));
        }
    }

}
