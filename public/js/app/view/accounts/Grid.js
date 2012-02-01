Ext.define('PoupaNiquel.view.accounts.Grid', {
	extend   : 'PoupaNiquel.view.common.DataGrid',

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
});