Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux', '/public/js/extjs/ux');
Ext.require(['Ext.ux.form.MultiSelect']);

Ext.define('PoupaNiquel.view.FilterPanel' ,{
	extend: 'Ext.panel.Panel',
	alias: 'widget.filterpanel',

    minWidth: 200,
    width: 250,
    split: true,
    border: false,
    frame: false,
    bodyPadding: 10,
    collapsible: true,
    autoScroll: true,
    layout: 'anchor',
    labelWidth: 5,
    defaultType: 'textfield',
    defaults: { anchor: '100%' },
     
    initComponent: function() {
    	this.items = [
	        {xtype: 'datefield', format: 'd/m/Y', name: 'startDate', emptyText: 'Start date'},
	        {xtype: 'datefield', format: 'd/m/Y', name: 'endDate', emptyText: 'End Date'},
	        {xtype: 'filtercombo', name:'account', emptyText:'Account', store:'Accounts'},
	        {xtype: 'filtercombo', name:'category', emptyText:'Category', store:'Categories'},
	        {xtype: 'filtercombo', name:'payee', emptyText:'Payee', store:'Payees'},
        ];
    	
        return this.callParent(arguments);
    }
});