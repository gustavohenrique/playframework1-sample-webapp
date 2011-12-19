Ext.define('PoupaNiquel.controller.Auth', {
    extend: 'Ext.app.Controller',

    models: ['User'],
    stores: ['Users'],
    views: ['auth.Signup', 'auth.Login'],
    
    init: function() {
    	this.control({
	        'loginPanel button[action=authenticate]': {
	            click: this.authenticate
	        },
	        'signupPanel button[action=register]': {
	            click: this.register
	        }
    	});
    },
    
    register: function(button) {
    	var form = button.up('form'),
    		values = form.getValues(),
    		store = this.getUsersStore();
    	
    	if (form.getForm().isValid()) {
    		store.insert(0, values);
    		console.log(store);
    	}
    },
    
    authenticate: function(button) {
    	var form = button.up('form').getForm();
    	if (form.isValid()) {
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
    }
    
});