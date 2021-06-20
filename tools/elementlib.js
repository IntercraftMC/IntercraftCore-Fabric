const gen = require("./genlib");

// Element Form Information ------------------------------------------------------------------------

const FORM = {
	// Items
	INGOT      : 1,
	NUGGET     : 1<<1,
	DUST       : 1<<2,
	DUST_SMALL : 1<<3,
	PLATE      : 1<<4,
	GEAR       : 1<<5,
	ROD        : 1<<6,
	RAW        : 1<<7,
	VIAL_LIQUID: 1<<8,
	VIAL_GAS   : 1<<9,

	// Blocks
	BLOCK        : 1<<10,
	FRAME        : 1<<11,
	ORE_STONE    : 1<<12,
	ORE_DEEPSLATE: 1<<13,
	RAW_BLOCK    : 1<<14,
}

const FORM_GROUP = {
	ORES: FORM.ORE_STONE | FORM.ORE_DEEPSLATE | FORM.RAW | FORM.RAW_BLOCK,
	DUST: FORM.DUST | FORM.DUST_SMALL,
	NONSOLID: FORM.VIAL_GAS | FORM.VIAL_LIQUID,
	COMPONENTS: FORM.GEAR | FORM.BLOCK | FORM.FRAME | FORM.PLATE | FORM.ROD,
	VANILLA: FORM.INGOT | FORM.NUGGET | FORM.BLOCK | FORM.ORE_STONE | FORM.ORE_DEEPSLATE | FORM.RAW | FORM.RAW_BLOCK
}

// Element Asset Generation ------------------------------------------------------------------------

/**
 * Generate assets of the available forms for the given element
 *
 * @param {string} name
 * @param {number} excludeForms
 */
 function element(name, excludeForms = 0) {
	let forms = Object.values(FORM_GROUP).reduce((forms, form) => forms | form);
	forms &= ~excludeForms;
	return Promise.all([
		elementBlocks(name, forms),
		elementItems(name, forms)
	]);
}

/**
 * Generate block assets of the available forms for the given element
 *
 * @param {string} name
 * @param {number} forms
 */
function elementBlocks(name, forms) {
	return Promise.all([
		gen.block(`${name}_ore`,           `block/ore`,           forms & FORM.ORE_STONE),
		gen.block(`${name}_deepslate_ore`, `block/deepslate_ore`, forms & FORM.ORE_DEEPSLATE),
	]);
}

/**
 * Generate assets of the available forms for the given element
 *
 * @param {string} name
 * @param {number} forms
 */
function elementItems(name, forms) {

}

// Element Generation ------------------------------------------------------------------------------

/**
 * Generate all element assets
 */
module.exports = function() {
	return Promise.all([
		/**
		 * Metals
		 */
		element("aluminium", FORM_GROUP.NONSOLID),
		element("copper", FORM_GROUP.NONSOLID | FORM_GROUP.VANILLA),
		element("gold", FORM_GROUP.NONSOLID | FORM_GROUP.VANILLA),
		element("iridium", FORM_GROUP.NONSOLID),
		element("iron", FORM_GROUP.NONSOLID | FORM_GROUP.VANILLA),
		element("lead", FORM_GROUP.NONSOLID),
		element("lithium", FORM_GROUP.NONSOLID | FORM_GROUP.COMPONENTS),
		element("mercury", FORM_GROUP.NONSOLID),
		element("nickel", FORM_GROUP.NONSOLID),
		element("silver", FORM_GROUP.NONSOLID),
		element("thorium", FORM_GROUP.NONSOLID | FORM.GEAR),
		element("tin", FORM_GROUP.NONSOLID),
		element("titanium", FORM_GROUP.NONSOLID | FORM_GROUP.ORES),
		element("tungsten", FORM_GROUP.NONSOLID),
		element("uranium", FORM_GROUP.NONSOLID | FORM.GEAR),
		element("zinc", FORM_GROUP.NONSOLID | FORM.GEAR),

		/**
		 * Non-metals
		 */
		element("carbon", FORM.VIAL_GAS | FORM.GEAR),
		element("silicon", FORM.VIAL_GAS | FORM.GEAR),

		/**
		 * Alloys
		 */
		element("brass", FORM_GROUP.NONSOLID | FORM_GROUP.ORES),
		element("bronze", FORM_GROUP.NONSOLID | FORM_GROUP.ORES),
		element("electrum", FORM_GROUP.NONSOLID | FORM_GROUP.ORES),
		element("steel", FORM_GROUP.NONSOLID | FORM_GROUP.ORES)
	]);
}
