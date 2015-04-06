package pl.polsl.i18n;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.architecture.WMachine;
import pl.polsl.utils.WMachineSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Translations provider. Parameter "lang" specifies
 * the target language. If "lang" does not contain
 * valid language name, then default language is returned.
 */
@WebServlet("/LanguageAccessor")
public class LanguageAccessor extends HttpServlet {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	
	/** Map containing available languages. */
	private Map<String, Language> languages = new HashMap<>();
    
	/** Gson instance used to serialize languages. */
	private final Gson GSON;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LanguageAccessor() {
        // readDefaultLanguage();
    	LanguageReader reader = new LanguageReader();
    	Language defaultLanguage = reader.readLanguage("pl-pl.lang"); 
    	languages.put("default", defaultLanguage);
    	final GsonBuilder gsonBuilder = new GsonBuilder();
    	gsonBuilder.registerTypeAdapter(Language.class, new LanguageSerializer(defaultLanguage));
        GSON = gsonBuilder.create();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter writer = response.getWriter();
    	String lang = request.getParameter("lang");
    	if(!languages.containsKey(lang))
    		lang = "default";
    	Language language = languages.get(lang);
		GSON.toJson(language, writer);
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
