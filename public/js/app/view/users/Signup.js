Ext.define('PoupaNiquel.view.users.Signup', {
	extend: 'Ext.window.Window',
    alias: 'widget.signup',

    title: 'Sign Up',
    width: 300,
    height: 200,
    layout: 'fit',
    border: 0,
    closable: false,
    plain: true,
    
    items: [{
    	xtype: 'form',
    	bodyPadding: 10,
    	
    	fieldDefaults: {
            labelWidth: 60,
            anchor: '100%',
            allowBlank: false,
            msgTarget: 'side',
            selectOnFocus: true,
            enableKeyEvents: true,
        },
        
    	items: [{
    		xtype: 'hiddenfield',
    		name: 'id',
    	}, {
    		fieldLabel: 'Name',
    		xtype: 'textfield',
    		name: 'fullname',
        }, {
    		fieldLabel: 'E-mail',
    		xtype: 'textfield',
    		name: 'username',
    		vtype: 'email'
        }, {
        	fieldLabel: 'Password',
           	xtype: 'textfield',
           	name: 'password',
           	inputType: 'password',
        }],
        
        
    }],
    
    initComponent: function() {
    	this.buttons = [{
            text: 'Ok',
            action: 'create',
            width: 100,
        }];
    	
        this.callParent(arguments);
    }
});