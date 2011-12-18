Ext.define('PoupaNiquel.controller.Auth', {
    extend: 'Ext.app.Controller',

    models: ['User'],
    stores: ['Users'],
    views: ['auth.Menu', 'auth.Login'],
    
    init: function() {
    	this.control({
	        'loginPanel button[action=authenticate]': {
	            click: this.authenticate
	        }
    	});
    },
    
    authenticate: function(button) {
    	var form = button.up('form').getForm();
    	form.submit({
    	    url: '/auth/authenticate',
            method: 'POST',
            scope:this,
            success: function(form, response) {
//                 loginWin.close();
                 console.log("ok " + action);
//                 Ext.ux.Router.redirect('');
            },
            failure: function(form, response) {
            	console.log(response)
            	Ext.Msg.alert('Error', response.result.message);
            }
    	});
    }
    
});