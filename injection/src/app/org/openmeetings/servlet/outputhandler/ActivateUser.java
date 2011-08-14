package org.openmeetings.servlet.outputhandler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.servlet.VelocityViewServlet;
import org.openmeetings.app.data.basic.Configurationmanagement;
import org.openmeetings.app.data.basic.Fieldmanagment;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.persistence.beans.lang.Fieldlanguagesvalues;
import org.openmeetings.app.persistence.beans.user.Users;
import org.openmeetings.app.remote.red5.ScopeApplicationAdapter;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivateUser extends VelocityViewServlet {
	private static final long serialVersionUID = -8892729047921796170L;
	private static Logger log = Red5LoggerFactory.getLogger(ActivateUser.class, ScopeApplicationAdapter.webAppRootKey);

	private Configurationmanagement cfgManagement;
	private Usermanagement userManagement;
	private Fieldmanagment fieldmanagment;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfgManagement = (Configurationmanagement)config.getServletContext().getAttribute("cfgManagement");
		userManagement = (Usermanagement)config.getServletContext().getAttribute("userManagement");
		fieldmanagment = (Fieldmanagment)config.getServletContext().getAttribute("fieldmanagment");
	}
	
	@Override
	public Template handleRequest(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Context ctx)
			throws ServletException, IOException {

		try {
			String hash = httpServletRequest.getParameter("u");
			ServletContext context = getServletContext();
			String loginURL = context.getInitParameter("webAppRootKey");

			ctx.put("APPLICATION_NAME", context.getServletContextName());
			if (hash == null) {
				// No hash
				Long default_lang_id = Long.valueOf(
						cfgManagement.getConfKey(3, "default_lang_id")
								.getConf_value()).longValue();
				Fieldlanguagesvalues labelid669 = fieldmanagment
						.getFieldByIdAndLanguage(new Long(669), default_lang_id);
				Fieldlanguagesvalues labelid672 = fieldmanagment
						.getFieldByIdAndLanguage(new Long(672), default_lang_id);

				ctx.put("message", labelid669.getValue());
				ctx.put("link",
						"<a href='" + loginURL + "'>" + labelid672.getValue()
								+ "</a>");
				return getVelocityEngine()
						.getTemplate("activation_template.vm");
			}
			//
			Users user = userManagement.getUserByActivationHash(hash);

			if (user == null) {
				// No User Found with this Hash
				Long default_lang_id = Long.valueOf(
						cfgManagement.getConfKey(3, "default_lang_id")
								.getConf_value()).longValue();

				Fieldlanguagesvalues labelid669 = fieldmanagment
						.getFieldByIdAndLanguage(new Long(669), default_lang_id);
				Fieldlanguagesvalues labelid672 = fieldmanagment
						.getFieldByIdAndLanguage(new Long(672), default_lang_id);

				ctx.put("message", labelid669.getValue());
				ctx.put("link",
						"<a href='" + loginURL + "'>" + labelid672.getValue()
								+ "</a>");
				return getVelocityEngine()
						.getTemplate("activation_template.vm");

			} else if (user.getStatus() == 1) {
				// already activated
				Long default_lang_id = Long.valueOf(
						cfgManagement.getConfKey(3, "default_lang_id")
								.getConf_value()).longValue();

				Fieldlanguagesvalues labelid670 = fieldmanagment
						.getFieldByIdAndLanguage(new Long(670), default_lang_id);
				Fieldlanguagesvalues labelid672 = fieldmanagment
						.getFieldByIdAndLanguage(new Long(672), default_lang_id);

				ctx.put("message", labelid670.getValue());
				ctx.put("link",
						"<a href='" + loginURL + "'>" + labelid672.getValue()
								+ "</a>");
				return getVelocityEngine()
						.getTemplate("activation_template.vm");

			} else if (user.getStatus() == 0) {
				// activate
				user.setStatus(1);
				user.setUpdatetime(new Date());

				userManagement.updateUser(user);

				Long default_lang_id = Long.valueOf(
						cfgManagement.getConfKey(3, "default_lang_id")
								.getConf_value()).longValue();

				Fieldlanguagesvalues labelid671 = fieldmanagment
						.getFieldByIdAndLanguage(new Long(671), default_lang_id);
				Fieldlanguagesvalues labelid672 = fieldmanagment
						.getFieldByIdAndLanguage(new Long(672), default_lang_id);

				ctx.put("message", labelid671.getValue());
				ctx.put("link",
						"<a href='" + loginURL + "'>" + labelid672.getValue()
								+ "</a>");
				return getVelocityEngine()
						.getTemplate("activation_template.vm");

			} else {
				// unkown Status
				Long default_lang_id = Long.valueOf(
						cfgManagement.getConfKey(3, "default_lang_id")
								.getConf_value()).longValue();

				Fieldlanguagesvalues labelid672 = fieldmanagment
						.getFieldByIdAndLanguage(new Long(672), default_lang_id);

				ctx.put("message", "Unkown Status");
				ctx.put("link",
						"<a href='" + loginURL + "'>" + labelid672.getValue()
								+ "</a>");
				return getVelocityEngine()
						.getTemplate("activation_template.vm");

			}

		} catch (Exception err) {
			log.error("[ActivateUser]", err);
			err.printStackTrace();
		}
		return null;
	}

}
