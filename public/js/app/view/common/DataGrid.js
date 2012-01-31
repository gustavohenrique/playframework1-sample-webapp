Ext.define('PoupaNiquel.view.common.DataGrid',{
	extend : 'Ext.grid.Panel',
    border : true,
    store  : [],
    
//    tbar: [{
//    	text     : 'New',
//    	iconCls  : 'new-icon',
//    	action   : 'add'
//    }, {
//        text     : 'Delete',
//        iconCls  : 'delete-icon',
//        action   : 'delete'
//    }],
    
    columns: [{
    	xtype    : 'rownumberer',
    	width    : 30,
    }, {
        text     : 'Name',
        dataIndex: 'name',
        flex: 1,
        sortable : true,
        field: {
            xtype: 'textfield'
        }
    }],
   
    initComponent: function() {
    	this.dockedItems = [{
    		xtype: 'toolbar',
    		items: [{
    			text: 'Add',
    			iconCls: 'new-icon',
    			action: 'add'
    		}, {
    			text: 'Delete',
    			iconCls: 'delete-icon',
    			action: 'delete'
			}]
		}, {
    		store          : this.getStore(),
    		displayInfo    : true,
            xtype          : 'pagingtoolbar',
            dock           : 'bottom',
            beforePageText : 'Page ',
            displayMsg     : '{0} - {1} of {2}',
            emptyMsg       : 'Nothing to display',
        }];
        return this.callParent(arguments);
    },
    
});