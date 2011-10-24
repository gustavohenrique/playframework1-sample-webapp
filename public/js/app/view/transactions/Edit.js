Ext.define('PoupaNiquel.view.transactions.Edit', {
    extend: 'Ext.window.Window',
    alias : 'widget.transactionEdit',

    title : 'Add/Edit Transaction',
    layout: 'fit',
    autoShow: true,
    width: 450,
    height: 300,
    modal: true,
    
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
            format: 'Y-m-d',
            submitFormat: 'Y-m-d',
            allowBlank: false,
        }, {
            xtype: 'textfield',
            name : 'description',
            fieldLabel: 'Description',
            allowBlank: false
        }, {
            xtype: 'numberfield',
            name : 'amount',
            fieldLabel: 'Amount',
            decimalPrecision: 2,
            allowBlank: false
        }, {
        	xtype: 'filterComboBox',
        	name:'payment',
        	fieldLabel: 'Payment',
        }, {
        	xtype: 'filterComboBox',
        	name:'categoryId',
        	fieldLabel: 'Category',
        	store:'Categories',
        }, {
        	xtype: 'filterComboBox',
        	name:'payeeId',
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
	            itemId: 'save',
	            text: 'Save',
	            action: 'save'
	        },{
	            text: 'Cancel',
	            scope: this,
	            handler: this.close
	        }]
	    }];
	    
	    return this.callParent(arguments);
    }
});