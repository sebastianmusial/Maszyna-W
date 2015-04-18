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
    	Language defaultLanguage = new DefaultLanguage(); 
    	languages.put("default", defaultLanguage);
    	final GsonBuilder gsonBuilder = new GsonBuilder();
    	final LanguageSerializer serializer = new LanguageSerializer(defaultLanguage);
    	gsonBuilder.registerTypeAdapter(Language.class, serializer);
    	gsonBuilder.registerTypeAdapter(DefaultLanguage.class, serializer);
        GSON = gsonBuilder.create();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> method.
     * Serializes language with name given in request's "lang" parameter and returns it.
     * If there is no language with that name, default language is returned.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("application/json;charset=UTF-8");
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
