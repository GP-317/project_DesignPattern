module project_DesignPattern {
	requires transitive javafx.graphics;
	requires javafx.fxml;
	requires transitive javafx.controls;
	
	opens controller to javafx.graphics,javafx.fxml;
	exports controller to javafx.graphics,javafx.fxml;
	
	opens view to javafx.graphics,javafx.fxml;
	exports view to javafx.graphics,javafx.fxml;
	
}