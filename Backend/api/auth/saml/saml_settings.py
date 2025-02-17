import os

def create_saml_settings():
    return {
        "strict": True,
        "debug": True,
        "sp": {
            "entityId": os.environ.get("SSO_SP_ENTITY_ID"),
            "assertionConsumerService": {
                "url": os.environ.get("SSO_SP_ENTITY_ID") + "saml/acs/",
                "binding": "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST",
            },
            "singleLogoutService": {
                "url": os.environ.get("SSO_SP_ENTITY_ID") + "saml/sls/",
                "binding": "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect",
            },
            "NameIDFormat": "urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified",
        },
        "idp": {
            "entityId": os.environ.get("SSO_IDP_ENTITY_ID"),
            "singleSignOnService": {
                "url": os.environ.get("SSO_MS_URL") + "saml2",
                "binding": "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect",
            },
            "singleLogoutService": {
                "url": os.environ.get("SSO_MS_URL") + "saml2",
                "binding": "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect",
            },
            "x509cert": os.environ.get("SSO_CERTIFICATE"),
        },
    }
