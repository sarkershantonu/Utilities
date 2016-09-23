

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.BooleanOptionHandler;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.SecureRandom;



public class Client {
    private static final String DEFAULT_GITHUB_HOST = "github.ldn.swissbank.com";
    
    private static final int ERROR_BAD_OPTIONS = -1;
    private static final int ERROR_IO_EXCEPTION = -2;

    @Option(name = "--hostname", metaVar = "<github_hostname>", usage = "The GitHub host to connect to\nDefaults to " + DEFAULT_GITHUB_HOST)
    private String hostname = DEFAULT_GITHUB_HOST;

    @Option(name = "--username", metaVar = "<username>", usage = "GitHub user account name")
    private String username;

    @Option(name = "--password", metaVar = "<password>", usage = "Password for the GitHub account")
    private String password;

    @Option(name = "--help", aliases = {"-h"}, handler= BooleanOptionHandler.class, usage = "Show this help text")
    private boolean showUsage = false;

    private void run(String[] args) {
        final CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println("Failed to parse command line arguments.");
            usageExit();
        }

        // print usage
        if (args == null || args.length == 0 || showUsage) {
            usageExit();
        }

        disableSSLCertificateValidation();

        GitHubClient client = new GitHubClient(hostname);

        // Prompt for credentials if not present.
        if (username == null) {
            username = getStringOption("username");
        }
        if (password == null) {
            password = getStringOption("password");
        }
        client.setCredentials(username, password);

        RepositoryService service = new RepositoryService(client);
        try {
            for(Repository repo : service.getRepositories("FX")) {
                System.out.println(repo.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(ERROR_IO_EXCEPTION);
        }

    }


    private void usageExit() {
        System.err.println("Connect to a GitHub server and show repositories");
        new CmdLineParser(this).printUsage(System.err);
        System.exit(ERROR_BAD_OPTIONS);
    }

    public static final void main(String[] args) {
        final Client client = new Client();
        client.run(args);
    }

}
