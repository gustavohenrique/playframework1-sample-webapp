Ext.define('PoupaNiquel.controller.Accounts', {
	extend        : 'PoupaNiquel.controller.Common',
    myModelName   : 'Account',
    myStoreName   : 'Accounts',
    myPanelName   : 'accountsPanel',
    myGridSelector: 'accountsGrid',
    myTitle       : 'Accounts',
    
    myGridPanel: Ext.create('PoupaNiquel.view.common.DataGrid', {
    	alias  : 'widget.' + this.myGridSelector,
    	plugins: Ext.create('Ext.grid.plugin.CellEditing'),
    	store  : Ext.create('PoupaNiquel.store.Accounts'),
    	
    	columns: [{
        	xtype: 'rownumberer',
        	width: 30,
        }, {
            text: 'Name',
            sortable: true,
            dataIndex: 'name',
            field: {
                xtype: 'textfield'
            }
        }, {
        	text : 'number',
        	flex : 1,
            sortable : false,
            dataIndex: 'number',
            field: {
                xtype: 'textfield'
            }
        }, {
        	text: 'Initial',
        	flex: 0,
            sortable: false,
            dataIndex: 'initial',
            width: 120,
            field: {
                xtype: 'textfield'
            }
        }],
    })
    
});