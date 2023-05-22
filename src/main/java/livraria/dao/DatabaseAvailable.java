package livraria.dao;

public enum DatabaseAvailable {
	DEFAULT(null), LIVRARIA("livraria");
	
	private final String database;

	private DatabaseAvailable(String database) {
		this.database = database;
	}

	public String getDatabase() {
		return database;
	}
	
}
