Ext.define('PoupaNiquel.view.TransactionForm', {
    extend: 'Ext.window.Window',
    alias : 'widget.transactionform',

    title : 'Edit Transaction',
    layout: 'fit',
    autoShow: true,
    width: 450,
    height: 300,
    
    items: [{
        xtype: 'form',
        padding: '5 5 0 5',
        border: false,
        style: 'background-color: #fff;',
        
        fieldDefaults: {
            anchor: '100%',
            labelAlign: 'left',
            allowBlank: true,
            combineErrors: true,
            msgTarget: 'side'
        },

        items: [{
		    xtype: 'textfield',
		    name : 'id',
		    fieldLabel: 'id',
		    hidden:true
		}, {
            xtype: 'datefield',
            name : 'transactionDate',
            fieldLabel: 'Date',
            format: 'd/m/Y',
            allowBlank: false
        }, {
            xtype: 'textfield',
            name : 'description',
            fieldLabel: 'Description',
            allowBlank: false
        }, {
            xtype: 'textfield',
            name : 'amount',
            fieldLabel: 'Amount',
            allowBlank: false
        }, {
        	xtype: 'filtercombo',
        	name:'payment',
        	fieldLabel: 'Payment',
        }, {
        	xtype: 'filtercombo',
        	name:'categoryId',
        	fieldLabel: 'Category',
        	store:'Categories',
        }, {
        	xtype: 'filtercombo',
        	name:'payee',
        	fieldLabel: 'Payee',
        	store:'Payees'
        }]
    }],
    
    initComponent: function() {
	    this.dockedItems = [{
	        xtype: 'toolbar',
	        dock: 'bottom',
	        id:'buttons',
	        ui: 'footer',
	        items: ['->', {
	            iconCls: 'icon-save',
	            itemId: 'save',
	            text: 'Save',
	            action: 'save'
	        },{
	            iconCls: 'icon-reset',
	            text: 'Cancel',
	            scope: this,
	            handler: this.close
	        }]
	    }];
	    
	    return this.callParent(arguments);
    }
});