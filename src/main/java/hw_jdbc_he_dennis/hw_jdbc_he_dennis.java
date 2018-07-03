package hw_jdbc_he_dennis;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.boot.SpringApplication;

import dao.DeveloperDao;
import dao.PageDao;
import dao.RoleDao;
import dao.UserDao;
import dao.WebsiteDao;
import dao.WidgetDao;
import edu.northeastern.cs5200.Cs5200Summer2018DennisheApplication;
import model.Developer;
import model.HeadingWidget;
import model.HtmlWidget;
import model.ImageWidget;
import model.Page;
import model.Phone;
import model.User;
import model.Website;
import model.Widget;
import model.YouTubeWidget;
import model.Role;


public class hw_jdbc_he_dennis {
	
	public static void main(String[] args) {
		SpringApplication.run(Cs5200Summer2018DennisheApplication.class, args);
		
		// 1. Create the following developers and users.
        Developer alice = new Developer("alice", "alice", "Alice", "Wonder", "alice@wonder.com", "4321rewq");
        Developer bob = new Developer("bob", "bob", "Bob", "Marley", "bob@marley.com", "5432trew");
        Developer charlie = new Developer("charlie", "charlie", "Charles", "Garcia", "chuch@garcia.com", "6543ytre");
        User dan = new User("dan", "dan", "Dan", "Martin", "dan@martin.com", "7654fda");
        User ed = new User("ed", "ed", "Ed", "Karaz", "ed@kar.com", "5678dfgh");
		
        DeveloperDao.getInstance().createDeveloper(alice);
        DeveloperDao.getInstance().createDeveloper(bob);
        DeveloperDao.getInstance().createDeveloper(charlie);
        UserDao.getInstance().createUser(dan);
        UserDao.getInstance().createUser(ed);
        
        
        // 2. Create the following web sites for the developers above.
        
        Date gradingDate = new Date(Calendar.getInstance().getTime().getTime());

        Website facebook = new Website("Facebook", "an online social media and social networking service", gradingDate, gradingDate, 1234234);
        Website twitter = new Website("Twitter", "an online news and social networking service", gradingDate, gradingDate, 4321543);
        Website wikipedia = new Website("Wikipedia", "a free online encyclopedia", gradingDate, gradingDate, 3456654);
        Website cnn = new Website("CNN", "an American basic cable and satellite television news channel", gradingDate, gradingDate, 6543345);
        Website cnet = new Website("CNET", "an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics", gradingDate, gradingDate, 5433455);
        Website gizmodo = new Website("Gizmodo", "a design, technology, science and science fiction website that also writes articles on politics", gradingDate, gradingDate, 4322345);

        //get developers with their ids
        alice = DeveloperDao.getInstance().findDeveloperByUsername(alice.getUsername());
        bob = DeveloperDao.getInstance().findDeveloperByUsername(bob.getUsername());
        charlie = DeveloperDao.getInstance().findDeveloperByUsername(charlie.getUsername());
        
        // create websites for owners
        WebsiteDao.getInstance().createWebsiteForDeveloper(alice.getId(), facebook);
        WebsiteDao.getInstance().createWebsiteForDeveloper(bob.getId(), twitter);
        WebsiteDao.getInstance().createWebsiteForDeveloper(charlie.getId(), wikipedia);
        WebsiteDao.getInstance().createWebsiteForDeveloper(alice.getId(), cnn);
        WebsiteDao.getInstance().createWebsiteForDeveloper(bob.getId(), cnet);
        WebsiteDao.getInstance().createWebsiteForDeveloper(charlie.getId(), gizmodo);
        
        // get new complete website for each website
        facebook = WebsiteDao.getInstance().findWebsiteByName(facebook.getName());
        twitter = WebsiteDao.getInstance().findWebsiteByName(twitter.getName());
        wikipedia = WebsiteDao.getInstance().findWebsiteByName(wikipedia.getName());
        cnn = WebsiteDao.getInstance().findWebsiteByName(cnn.getName());
        cnet = WebsiteDao.getInstance().findWebsiteByName(cnet.getName());
        gizmodo = WebsiteDao.getInstance().findWebsiteByName(gizmodo.getName());
  
        //assign other roles to each developer
        RoleDao.getInstance().assignWebsiteRole(bob.getId(), facebook.getId(), Role.RoleType.EDITOR);
        RoleDao.getInstance().assignWebsiteRole(charlie.getId(), facebook.getId(), Role.RoleType.ADMIN);
        RoleDao.getInstance().assignWebsiteRole(charlie.getId(), twitter.getId(), Role.RoleType.EDITOR);
        RoleDao.getInstance().assignWebsiteRole(alice.getId(), twitter.getId(), Role.RoleType.ADMIN);
        RoleDao.getInstance().assignWebsiteRole(alice.getId(), wikipedia.getId(), Role.RoleType.EDITOR);
        RoleDao.getInstance().assignWebsiteRole(bob.getId(), wikipedia.getId(), Role.RoleType.ADMIN);
        RoleDao.getInstance().assignWebsiteRole(bob.getId(), cnn.getId(), Role.RoleType.EDITOR);
        RoleDao.getInstance().assignWebsiteRole(charlie.getId(), cnn.getId(), Role.RoleType.ADMIN);
        RoleDao.getInstance().assignWebsiteRole(charlie.getId(), cnet.getId(), Role.RoleType.EDITOR);
        RoleDao.getInstance().assignWebsiteRole(alice.getId(), cnet.getId(), Role.RoleType.ADMIN);
        RoleDao.getInstance().assignWebsiteRole(alice.getId(), gizmodo.getId(), Role.RoleType.EDITOR);
        RoleDao.getInstance().assignWebsiteRole(bob.getId(), gizmodo.getId(), Role.RoleType.ADMIN);
        
       // 3. Create the following pages for the web sites above. 
        Date startingDate = Date.valueOf("2018-01-08");
        Date dueDate = Date.valueOf("2018-03-04");
        
        Page home = new Page("Home", "Landing page", startingDate, dueDate, 123434);
        Page about = new Page("About", "Website description", startingDate, dueDate, 234545);
        Page contact = new Page("Contact", "Addresses, phones, and contact info", startingDate, dueDate, 345656);
        Page preferences = new Page("Preferences", "Where users can configure their preferences", startingDate, dueDate, 456776);
        Page profile = new Page("Profile", "Users can configure their personal information", startingDate, dueDate, 567878);

//        // creat pages
        PageDao.getInstance().createPageForWebsite(cnet.getId(), home);
        PageDao.getInstance().createPageForWebsite(gizmodo.getId(), about);
        PageDao.getInstance().createPageForWebsite(wikipedia.getId(), contact);
        PageDao.getInstance().createPageForWebsite(cnn.getId(), preferences);
        PageDao.getInstance().createPageForWebsite(cnet.getId(), profile);
//
        // get pageId
        List<Page> pages =  (List<Page>) PageDao.getInstance().findPagesForWebsite(cnet.getId());
        home = pages.get(0);
        
        pages = (List<Page>) PageDao.getInstance().findPagesForWebsite(gizmodo.getId());
        about = pages.get(0);
        
        pages = (List<Page>) PageDao.getInstance().findPagesForWebsite(wikipedia.getId());
        contact = pages.get(0);
        
        pages = (List<Page>) PageDao.getInstance().findPagesForWebsite(cnn.getId());
        preferences = pages.get(0);
        
        pages = (List<Page>) PageDao.getInstance().findPagesForWebsite(cnet.getId());
        profile = pages.get(1);
        
        // assign pagerole to each developer
        RoleDao.getInstance().assignPageRole(alice.getId(), home.getId(), Role.RoleType.EDITOR);
        RoleDao.getInstance().assignPageRole(bob.getId(), home.getId(), Role.RoleType.REVIEWER);
        RoleDao.getInstance().assignPageRole(charlie.getId(), home.getId(), Role.RoleType.WRITER);
        RoleDao.getInstance().assignPageRole(bob.getId(), about.getId(), Role.RoleType.EDITOR);
        RoleDao.getInstance().assignPageRole(charlie.getId(), about.getId(), Role.RoleType.REVIEWER);
        RoleDao.getInstance().assignPageRole(alice.getId(), about.getId(), Role.RoleType.WRITER);
        RoleDao.getInstance().assignPageRole(charlie.getId(), contact.getId(), Role.RoleType.EDITOR);
        RoleDao.getInstance().assignPageRole(alice.getId(), contact.getId(), Role.RoleType.REVIEWER);
        RoleDao.getInstance().assignPageRole(bob.getId(), contact.getId(), Role.RoleType.WRITER);
        RoleDao.getInstance().assignPageRole(alice.getId(), preferences.getId(), Role.RoleType.EDITOR);
        RoleDao.getInstance().assignPageRole(bob.getId(), preferences.getId(), Role.RoleType.REVIEWER);
        RoleDao.getInstance().assignPageRole(charlie.getId(), preferences.getId(), Role.RoleType.WRITER);
        RoleDao.getInstance().assignPageRole(bob.getId(), profile.getId(), Role.RoleType.EDITOR);
        RoleDao.getInstance().assignPageRole(charlie.getId(), profile.getId(), Role.RoleType.REVIEWER);
        RoleDao.getInstance().assignPageRole(alice.getId(), profile.getId(), Role.RoleType.WRITER);
        
        // 4. Create the following widgets for the pages shown.
        HeadingWidget head123 = new HeadingWidget ("head123", "Welcome", 0);
        HtmlWidget post234 =  new HtmlWidget("post234", "<p>Lorem</p>", 0);
        HeadingWidget head345 = new HeadingWidget("head345", "Hi", 1);
        HtmlWidget intro456 = new HtmlWidget("intro456", "<h1>Hi</h1>", 2);
        
        ImageWidget image345 = new ImageWidget("image345", null, 3, "/img/567.png");
        image345.setWidth(50);
        image345.setHeight(100);
        
        YouTubeWidget video456 = new YouTubeWidget("video456", null, 0, "https://youtu.be/h67VX51QXiQ");
        video456.setWidth(400);
        video456.setHeight(300);
        
        // create widget for pages
        WidgetDao.getInstance().createWidgetForPage(home.getId(), head123);
        WidgetDao.getInstance().createWidgetForPage(about.getId(), post234);
        WidgetDao.getInstance().createWidgetForPage(contact.getId(), head345);
        WidgetDao.getInstance().createWidgetForPage(contact.getId(), intro456);
        WidgetDao.getInstance().createWidgetForPage(contact.getId(), image345);
        WidgetDao.getInstance().createWidgetForPage(preferences.getId(), video456);
        
        // 5. Implement Updates: 
        // Using the DAOs implemented earlier, do the following updates
        Phone phone = new Phone("333-444-555", 1);
        DeveloperDao.getInstance().updatePhoneForDeveloper(charlie.getId(), phone);
        
        // Update widget - Update the relative order of widget head345 on the page so that it's new order is 3. 
        // Note that the other widget's order needs to update as well
        List<Widget> widgets = (List<Widget>) WidgetDao.getInstance().findWidgetsForPage(contact.getId());
        for(Widget widget : widgets) {
            if(widget.getName().equals("head345")) {
            		widget.setOrder(3);
            }else{
            		widget.setOrder(widget.getOrder()-1);
            }
            WidgetDao.getInstance().updateWidget(widget.getId(), widget);
        }
        
        // Update page - Append 'CNET - ' to the beginning of all CNET's page titles
        List<Page> cnetPages = (List<Page>) PageDao.getInstance().findPagesForWebsite(cnet.getId());
        for (Page page: cnetPages ) {
        		page.setName("CNET-" + page.getName());
        		PageDao.getInstance().updatePage(page.getId(), page);
        }
        
        //Update roles - Swap Charlie's and Bob's role in CNET's Home page
        Page homepage = null;
        
        for (Page page: cnetPages) {
        		if(page.getName().equals("CNET-Home")) {
        			homepage = page;
        			break;
        		}
        }
        
        Role firstDevRole = RoleDao.getInstance().findPageRoleForDeveloper(bob.getId(), homepage.getId());   
        Role secondDevRole = RoleDao.getInstance().findPageRoleForDeveloper(charlie.getId(), homepage.getId());
   
        RoleDao.getInstance().updatePageRole(firstDevRole.getDeveloperId(), homepage.getId(), secondDevRole.getRole());
        RoleDao.getInstance().updatePageRole(secondDevRole.getDeveloperId(), homepage.getId(), firstDevRole.getRole());

        // 6. Implement Deletes
        // Delete Alice's primary address
        DeveloperDao.getInstance().deleteAddressForDeveloper(alice.getId(), 1);
        
        //Remove the last widget in the Contact page. The last widget is the one with the highest value in the order field
        List<Widget> contactWidgets = (List<Widget>) WidgetDao.getInstance().findWidgetsForPage(contact.getId());
        int max = -1;
        int maxId = 0;
        for(Widget w : contactWidgets) {
            if(w.getOrder() > max) {
                max = w.getOrder();
                maxId = w.getId();
            }
        }
        System.out.println(maxId);
        
        WidgetDao.getInstance().deleteWidget(maxId);
        
        // Delete page - Remove the last updated page in Wikipedia
        List<Page> wikiPages = (List<Page>) PageDao.getInstance().findPagesForWebsite(wikipedia.getId());
        Date lastedUpdated = Date.valueOf("2018-01-01");
        int targetId = 0;
        for(Page p : wikiPages) {
            if(p.getUpdated().compareTo(lastedUpdated) > 0) {
                lastedUpdated = p.getUpdated();
                targetId = p.getId();
            }
        }
        PageDao.getInstance().deletePage(targetId);
        
        //Remove the CNET website, as well as all related roles and privileges relating developers to the Website and Pages
        WebsiteDao.getInstance().deleteWebsite(cnet.getId());
        
	}
	
}

