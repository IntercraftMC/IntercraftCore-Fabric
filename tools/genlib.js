const fs = require("fs");
const path = require("path");

// Generation Information --------------------------------------------------------------------------

// Path to the mod's assets
const ASSETS_PATH = path.join(__dirname, "../src/main/resources/assets/intercraftcore");

// Utility Functions -------------------------------------------------------------------------------

/**
 * Write the given content to a file
 */
function writeJsonFile(filePath, content) {
	return new Promise((resolve, reject) => {
		let file = path.join(ASSETS_PATH, filePath + '.json');
		fs.mkdirSync(path.dirname(file), { recursive: true });
		fs.writeFile(
			file,
			JSON.stringify(content, undefined, 2),
			(err) => { err ? reject(err) : resolve() }
		)
	});
}

// Asset Generation --------------------------------------------------------------------------------

/**
 * Generate all assets required for a block
 *
 * @param {string}  name
 * @param {string}  model
 * @param {boolean|number} shouldBeIncluded
 */
function block(name, model, shouldBeIncluded) {
	if (!shouldBeIncluded) {
		return null;
	}
	return Promise.all([
		writeJsonFile(`blockstates/${name}`, {
			"variants": {
				"": {
					"model": `intercraftcore:${model}`
				}
			}
		}),
		item(name, model, true)
	])
}
/**
 * Generate all assets required for an item
 *
 * @param {string}  name
 * @param {string}  model
 * @param {boolean|number} shouldBeIncluded
 */
function item(name, model, shouldBeIncluded) {
	if (!shouldBeIncluded) {
		return null;
	}
	return writeJsonFile(`models/item/${name}`, {
		"parent": `intercraftcore:${model}`
	});
}

// Module Exports ----------------------------------------------------------------------------------

module.exports = {
	block,
	item
};
