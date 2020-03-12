import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamMemberTest {
    private TeamMember myMember;

    @BeforeEach
    public void setUp() {
        myMember = new TeamMember("John");
    }

    @Test
    public void addBusyBlocksOutOfRange_errorMessage() {

    }
}
