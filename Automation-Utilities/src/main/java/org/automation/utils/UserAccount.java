public enum UserAccount {
        normal("user3", "pass"), 
		admin("user1", "pass"),
        modarator("user2", "pass");

        private UserAccount(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        private String userName = "";
        private String password = "";
    }
