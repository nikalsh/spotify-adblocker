package main;

/**
 *
 * @author nikalsh
 */
public class SpotifyBlocker {

    private static String hostsToBlock
            = "####SPOTIFYBLOCKER####%n"
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
            + "####SPOTIFYBLOCKER####";
    
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final String SYSROOT = System.getenv("SystemDrive");

    private static final String MAC = "/etc/hosts";
    private static final String GNULINUX = "/etc/hosts";
    private static final String WINDOWS = SYSROOT+ ":/Windows/System32/drivers/etc/hosts";
    
    public SpotifyBlocker(String option) {

        
        String hostPath = (OS.contains("windows") ? WINDOWS : (OS.contains("mac") ? MAC : GNULINUX));
        
        
        
        switch (option.toLowerCase()) {
            case "block":

                break;

            case "unblock":
                break;

            default:
                System.out.format("invalid argument: %s%nexiting..%n", option);
                System.out.println(OS);
                System.out.println(SYSROOT);
                break;

        }
    }

    public static void main(String[] args) {

        String option = args[0];

        SpotifyBlocker sb = new SpotifyBlocker(option);

    }

}
