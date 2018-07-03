package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.HeadingWidget;
import model.HtmlWidget;
import model.ImageWidget;
import model.Widget;
import model.YouTubeWidget;

public class WidgetDao extends CommonDao {
	public static WidgetDao instance = null;
	public static WidgetDao getInstance() {
		if (instance == null) {
			instance = new WidgetDao();
		}
		return instance;
	}
	private WidgetDao() {};
	
	/**
	 * Insert a widget for certain page
	 * @param pageId the id of the page
	 * @param widget the widget to insert
	 * @return 1 of inserted, 0 if not
	 */
	public int createWidgetForPage(int pageId, Widget widget) {
		int result1 = 0;
		int result2 = 0;
		
		try {
			// Insert info into widget table
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String CREATE_WIDGET = "INSERT INTO widget (name, text, width, height, cssClass, cssStyle, widget.order, pageId) VALUES (?,?,?,?,?,?,?,?)";

			prepStatement = connection.prepareStatement(CREATE_WIDGET);			
			prepStatement.setString(1, widget.getName());
			prepStatement.setString(2, widget.getText());
			prepStatement.setInt(3, widget.getWidth());
			prepStatement.setInt(4, widget.getHeight());
			prepStatement.setString(5, widget.getCssClass());
			prepStatement.setString(6, widget.getCssStyle());
			prepStatement.setInt(7, widget.getOrder());
			prepStatement.setInt(8, pageId);
			result1 = prepStatement.executeUpdate();
			
			// Get widget id for updating subwidget table
			String FIND_WIDGETID_BY_NAME = "SELECT * FROM widget where name = ?";
			prepStatement = connection.prepareStatement(FIND_WIDGETID_BY_NAME);
			prepStatement.setString(1, widget.getName());
			ResultSet results = prepStatement.executeQuery();
			
			int id = 0;
			while (results.next()) {
				id = results.getInt("id");
			}
			
			// Insert info into corresponding type of widget's table
			if(widget instanceof  HeadingWidget) {
				
				String CREATE_HEADING = "INSERT INTO heading (size, widgetId) VALUES(?,?)";
				prepStatement = connection.prepareStatement(CREATE_HEADING);
				prepStatement.setInt(1, ((HeadingWidget) widget).getSize());
				prepStatement.setInt(2, id);
				result2 = prepStatement.executeUpdate();
				
			} else if (widget instanceof  HtmlWidget) {
				
				String CREATE_HTML = "INSERT INTO html (html, widgetId) VALUES(?,?)";
				prepStatement = connection.prepareStatement(CREATE_HTML);
				prepStatement.setString(1, ((HtmlWidget) widget).getHtml());
				prepStatement.setInt(2, id);
				result2 = prepStatement.executeUpdate();
				
			} else if (widget instanceof ImageWidget) {
				
				String CREATE_IMAGE = "INSERT INTO image (src, widgetId) VALUES(?,?)";
				prepStatement = connection.prepareStatement(CREATE_IMAGE);
				prepStatement.setString(1, ((ImageWidget) widget).getSrc());
				prepStatement.setInt(2, id);
				result2 = prepStatement.executeUpdate();
				
			} else if (widget instanceof YouTubeWidget) {
				
				String CERATE_YOUTUBE = "INSERT INTO youtube (url, shareable, expandable, widgetId) VALUES(?,?,?,?)";
				prepStatement = connection.prepareStatement(CERATE_YOUTUBE);
				prepStatement.setString(1, ((YouTubeWidget) widget).getUrl());
				prepStatement.setInt(2, ((YouTubeWidget) widget).isShareable());
				prepStatement.setInt(3, ((YouTubeWidget) widget).isExpandable());
				prepStatement.setInt(4, id);
				result2 = prepStatement.executeUpdate();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Math.min(result1, result2);
	}
	
	
	/**
	 * Find all widgets
	 * @return all widgets
	 */
	public Collection<Widget> findAllWidgets() {
		Collection<Widget> result = new ArrayList<Widget>();
	
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String FIND_ALL_WIDGETS = "SELECT * FROM widget";
			prepStatement = connection.prepareStatement(FIND_ALL_WIDGETS);
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String text = results.getString("text");
				int order = results.getInt("order");
				int width = results.getInt("width");
				int height = results.getInt("height");
				String cssClass = results.getString("cssClass");
				String cssStyle = results.getString("cssStyle");
				
				Widget widget = new Widget(id, name, text, order, width, height, cssClass, cssStyle);
				
				result.add(widget);
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;	
	}
	
	/**
	 * find a widget by its id
	 * @param widgetId its id
	 * @return the widget with the id
	 */
	public Widget findWidgetById(int widgetId) {
		Widget result = null;
		
		try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            
            String FIND_WIDGET_BY_ID = "SELECT * FROM widget WHERE id = ?";
            prepStatement = connection.prepareStatement(FIND_WIDGET_BY_ID);
            prepStatement.setInt(1, widgetId);
            ResultSet results = prepStatement.executeQuery();

            while(results.next()) {
            		int id = results.getInt("id");
				String name = results.getString("name");
				String text = results.getString("text");
				int order = results.getInt("order");
				int width = results.getInt("width");
				int height = results.getInt("height");
				String cssClass = results.getString("cssClass");
				String cssStyle = results.getString("cssStyle");
            		
				result = new Widget(id, name, text, order, width, height, cssClass, cssStyle);
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return result;
	}
	
	/**
	 * Find all widgets for a page
	 * @param pageId the id of the page
	 * @return all widgets in the page
	 */
	public Collection<Widget> findWidgetsForPage(int pageId) {
		Collection<Widget> result = new ArrayList<Widget>();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String FIND_ALL_WIDGETS = "SELECT * FROM widget WHERE pageId = ?";
			prepStatement = connection.prepareStatement(FIND_ALL_WIDGETS);
			prepStatement.setInt(1, pageId);
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String text = results.getString("text");
				int order = results.getInt("order");
				int width = results.getInt("width");
				int height = results.getInt("height");
				String cssClass = results.getString("cssClass");
				String cssStyle = results.getString("cssStyle");
				
				Widget widget = new Widget(id, name, text, order, width, height, cssClass, cssStyle);
				
				result.add(widget);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * Update the widget
	 * @param widgetId the id of the widget
	 * @param widget the new widget
	 * @return 1 if updated, 0 if not
	 */
	public int updateWidget(int widgetId, Widget widget) {
		int result1 = 0;
		int result2 = 0;
		
		try {
			// Insert info into widget table
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String UPDATE_WIDGET = "UPDATE widget SET  name = ?, text = ?, widget.order = ?, width = ?, height = ?, cssClass = ?, cssStyle = ? where id = ?";

			prepStatement = connection.prepareStatement(UPDATE_WIDGET);
			
			prepStatement.setString(1, widget.getName());
			prepStatement.setString(2, widget.getText());
			prepStatement.setInt(3, widget.getOrder());
			prepStatement.setInt(4, widget.getWidth());
			prepStatement.setInt(5, widget.getHeight());
			prepStatement.setString(6, widget.getCssClass());
			prepStatement.setString(7, widget.getCssStyle());
			prepStatement.setInt(8, widgetId);
			
			result1 = prepStatement.executeUpdate();
			
			// Insert info into corresponding type of widget's table
			if(widget instanceof  HeadingWidget) {
				
				String CREATE_HEADING = "INSERT INTO heading (size, widgetId) VALUES(?,?)";
				prepStatement = connection.prepareStatement(CREATE_HEADING);
				prepStatement.setInt(1, ((HeadingWidget) widget).getSize());
				prepStatement.setInt(2, widgetId);
				result2 = prepStatement.executeUpdate();
				
			} else if (widget instanceof  HtmlWidget) {
				
				String CREATE_HTML = "INSERT INTO html (html, widgetId) VALUES(?,?)";
				prepStatement = connection.prepareStatement(CREATE_HTML);
				prepStatement.setString(1, ((HtmlWidget) widget).getHtml());
				prepStatement.setInt(2, widgetId);
				result2 = prepStatement.executeUpdate();
				
			} else if (widget instanceof ImageWidget) {
				
				String CREATE_IMAGE = "INSERT INTO image (src, widgetId) VALUES(?,?)";
				prepStatement = connection.prepareStatement(CREATE_IMAGE);
				prepStatement.setString(1, ((ImageWidget) widget).getSrc());
				prepStatement.setInt(2, widgetId);
				result2 = prepStatement.executeUpdate();
				
			} else if (widget instanceof YouTubeWidget) {
				
				String CERATE_YOUTUBE = "INSERT INTO youtube (url, shareable, expandable, widgetId) VALUES(?,?,?,?)";
				prepStatement = connection.prepareStatement(CERATE_YOUTUBE);
				prepStatement.setString(1, ((YouTubeWidget) widget).getUrl());
				prepStatement.setInt(2, ((YouTubeWidget) widget).isShareable());
				prepStatement.setInt(3, ((YouTubeWidget) widget).isExpandable());
				prepStatement.setInt(4, widgetId);
				result2 = prepStatement.executeUpdate();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Math.max(result1, result2);
	}
	
	/**
	 * Delete a widget
	 * @param widgetId the id of the widget
	 * @return 1 if deleted, 0 if not
	 */
	public int deleteWidget(int widgetId) {
		int result = 0;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String  DELETE_WIDGET = "DELETE FROM widget WHERE id = ?";
			prepStatement = connection.prepareStatement(DELETE_WIDGET);
			prepStatement.setInt(1, widgetId);
			
			result = prepStatement.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
}
