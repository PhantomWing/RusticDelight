# 1.7.0
From colorful bell peppers to cozy cups of coffee, Rustic Delight 1.7 is packed with vibrant new bell pepper varieties, comforting recipes, world generation improvements, and plenty of delightful surprises waiting to be discovered.

### Additions
- Added mutated variants of the bell pepper crop
  - **Pale Bell Pepper Seeds**: Yields white, pink and orange bell peppers
  - **Dark Bell Pepper Seeds**: Yields black, blue and purple bell peppers
- Added **Wild Pale Bell Peppers** and **Wild Dark Bell Peppers**, which can be found in jungles (rare)
- Added **Bell Pepper Medley**, a new feast crafted with 1 of each stuffed bell pepper color
- Added **Giant Bell Pepper** block for each color, crafted with Bell Pepper Slice and randomly found in jungles (rare)
  - Configurable with the `bell_pepper_block_patch_chance` config option (set to zero to turn off spawning)
- Added two new types of coffee: **Pumpkin Coffee** and **Cherry Blossom Coffee**
- Added **Fried Fish**: new fried food crafted with any safe fish, Batter and Cooking Oil
- Added **Coffee Cheesecake** and **Slice of Coffee Cheesecake**
- Added **Coffee Pancakes** and **Coffee Pancake**
  - In addition, you can now add additional pancakes onto a default pile of 6, by shift-right-clicking while holding a pancake (to a maximum of 12).
- Added **Calamari Soup**
- Added **Calamari Crate**, a storage block for Calamari
- Rustic Delight's crops now randomly spawn in village farms (turn off with the `generate_village_farm_crops` config option)
- Rustic Delight's items now randomly generate as loot in chests (turn off with the `generate_random_loot` config option)
- Added advancements to guide new players through the features of Rustic Delight (gated by the various feature toggles)
- Added new feature toggles to the config (so you can disable features, making related items unobtainable)
  - `enable_bell_peppers`
  - `enable_cherry_blossom_foods`
  - `enable_coffee`
  - `enable_cotton`
  - `enable_fried_foods`
  - `enable_pancakes`
  - `enable_potato_slices`
  - `enable_syrup_foods`
- Added 3D models for many of the new items added in this update (requires Brewin' and Chewin' coaster)
- Added compatibility with Hearth and Harvest: its Syrup Bottle can be used in place of Syrup
- Added Ukrainian translations (thank you hikarosato!)
- Updated Japanese translations (thank you HayaKoh!)

### Changes
- You no longer need a knife to get a single pancake: Pancakes are now taken from the block by right-clicking, instead of being eaten directly.
- Taking a pancake from a placed stack puts it straight into your inventory
- Breaking a pancake block now drops the remaining pancakes as individual pancake items
- Updated Rice Roll Royale texture & models to match Farmer's Delight latest Rice Roll Medley changes
- Rebalanced coffee recipes & effects
- Rebalanced cheesecake recipes
- Improved Cherry Blossom Pancakes model
- Improved various textures, such as Cotton Boll, Wild Bell Peppers, Coffee Beans
- Added particles when eating/taking pancakes from the placed block
- Villagers can now consume Rustic Delight crops, making them compatible for automated villager farms
- The Wandering Trader now rarely offers Pale and Dark Bell Pepper Seeds

### Fixes
- Potion of Haste is now brewed from an Awkward Potion rather than straight from a Water Bottle, matching every other potion
- Coffee once again requires Roasted Coffee Beans: raw beans were accepted by mistake in 1.6.0
- Calamari Rolls once again require Calamari Slices, instead of also accepting whole Calamari


# 1.6.0
### Changes
- Updated to Farmer's Delight Refabricated 2.5.5 (now required, along with Fabric Loader 0.18.2+ and Fabric API 0.92.8+)
- Bell Pepper Soup now grants Nourishment instead of Comfort, matching Farmer's Delight 1.3 (Comfort was retired)
- Pancake blocks, cheesecakes, and Rice Roll Royale now show the italicized "Placeable" tooltip, matching Farmer's Delight feasts and pies
- Cherry Blossom Cheesecake and Syrup Cheesecake slices show a Speed effect tooltip
- Updated Bell Pepper and Honey Coffee textures

- Honey Coffee no longer requires Sugar, matching the other editions
- Simplified the Pancakes and Pumpkin Pancakes crafting patterns, matching the other editions
- Raw Coffee Beans can now be used to brew coffee
- Cooking Oil can now also be made from Pumpkin Seeds (plus canola and sunflower seed support for other mods)
- Syrup can now also be made from beetroots
- Whole Calamari can now be used to craft Calamari Rolls
- Made more items compostable: bell pepper slices, Roasted Coffee Beans, Coffee Cookie, Syrup Cookie, Syrup Cheesecake and its slice
- Wild Bell Peppers patches now generate slightly larger
- Updated the pancake block models and various textures to match the NeoForge edition

### Fixes
- Fixed bell peppers dropping only 1 slice when cut, instead of 2
- Fixed Bell Pepper Soup recipes: the crafting recipe requires 6 bell peppers again, and the Cooking Pot recipe needs 3 instead of 4
- Fixed Vegetable Pancakes and Sweet Salad requiring the wrong mix of vegetables and greens
- Fixed food stats deviating from the other editions: Golden Coffee Beans, Fruit Beignet, Honey/Chocolate/Vegetable Pancake effects, salad Regeneration, Bell Pepper Pasta Nourishment
- Fixed the coffee crop tags marking Roasted Coffee Beans as the crop instead of Coffee Beans
- Fixed Coffee Cookie and Syrup Cookie missing from the cookie tag
- Fixed Syrup Sandwich only accepting plain bread instead of any bread
- Fixed the Roasted Coffee Beans Bag missing from the straw blocks and storage blocks tags
- Fixed syrup items missing from Create's belt display tag
- Added missing Cutting Board recipes: Roasted Bell Peppers can now be cut into slices, and placeable pancake stacks can be cut into single pancakes
- Added missing recipe unlock advancements for pancakes, syrup foods, fried foods, bell pepper rolls and more
- Fixed broken Milk Coffee, Chocolate Coffee, Honey Coffee, and Syrup Coffee tooltips in English
- Fixed broken models for cheesecakes, Bell Pepper and Coffee crops
- Fixed Spring Rolls and Stuffed Bell Peppers being uncraftable, after Farmer's Delight removed its cabbage roll ingredients tag
- Fixed Batter, Potato Salad and Fried Rice being uncraftable, as they required an egg tag that does not exist on this version
- Fixed the creative tab name not being translated outside English
- Added missing tag translations (Fried Rice Ingredients, Fruits and Berries, Mushroom Rice Ingredients) to all languages
- Removed a duplicate Fried Rice recipe

# 1.5.0
### Additions
- Overhauled the pancake feature:
  - Added ability to take a single pancake from a pancakes block, with a knife
  - Added Cooking Pot recipes for all pancakes
  - Added new pancake variants: Pancakes (the classic variant), Pumpkin Pancakes
  - Added Pancake, Honey Pancake, Chocolate Pancake, Vegetable Pancake, Cherry Blossom Pancake, Pumpkin Pancake
  - Updated all pancake models
- Added a new cooking ingredient: Syrup! Required for some new items:
  - Pancakes and Pumpkin Pancakes require Syrup to craft
  - Syrup Cheesecake
  - Syrup Cookie
  - Syrup Sandwich
  - Syrup Coffee
- Added Coffee Cookie
- Added Sweet Salad
- Added slices for Bell Peppers (Red/Green/Yellow Bell Pepper Slice)
- Added slices for Roasted Bell Peppers (Roasted Red/Green/Yellow Bell Pepper Slice)
- Added Bell Pepper Roll variants (Red/Green/Yellow)
- Added Rice Roll Royale (a feast block)
- Added Fried Dough and Fried Dumplings
- Added configuration options via Cloth Config:
  - `squids_drop_calamari` to disable squids dropping Calamari
  - `wild_cotton_chance`, used to configure Wild Cotton spawning. Set to zero to disable the feature.
  - `wild_bell_peppers_chance`, used to configure Wild Bell Peppers spawning. Set to zero to disable the feature.
  - `wild_coffee_chance`, used to configure Wild Coffee spawning. Set to zero to disable the feature.
  - `enable_villager_trades`, `enable_wandering_trader_trades`, `enable_potions`
- Added a custom placement modifier `rusticdelight:configurable_rarity_filter` so wild crop spawn rates can be controlled via config at runtime
- Added compatibility with Brewin' and Chewin':
  - Added Coaster models for most of Rustic Delight's items, allowing you to place them in your world!
  - Includes Coaster models for Wheat Dough, Dumplings, Cabbage Rolls
- Added biome tags for Wild crop spawning:
  - Wild Cotton now also spawns in biomes tagged with `#c:is_forest`
  - Wild Bell Peppers & Wild Coffee now also spawn in biomes tagged with `#c:is_jungle`
- Added new tags: `rusticdelight:syrup`, `rusticdelight:syrup_ingredients`, `rusticdelight:sweet_liquids`, `rusticdelight:fruits_berries`, `rusticdelight:raw_cooked_eggs`, `rusticdelight:mushroom_rice_ingredients`
- Added Bell Pepper Soup to the `#c:foods/soup` tag
- Added Potato Slices to the `#c:crops/potato` tag for better cross-mod compatibility
- Overridden some Farmer's Delight recipes to allow Potato Slices and additional ingredients: Baked Cod Stew, Beef Stew, Vegetable Soup, Mushroom Rice, Fried Rice
- Bell Pepper Slices can now be used to cook Spring Rolls and Cabbage Rolls (added to `farmersdelight:cabbage_roll_ingredients`)
- Updated bag textures for Cotton Seeds, Bell Pepper Seeds, Coffee Beans, and Roasted Coffee Beans bags
- Updated textures for Coffee Beans, Roasted Coffee Beans, Golden Coffee Beans, and Bell Peppers (Green/Yellow/Red)
- Added missing translation keys for tags, potted wild crops, and crop blocks
- Added Russian translations
- Added Kazakh translations
- Added Korean translations
- Added Spanish (Argentina) translations
- Added Chilean Spanish translations

### Changes
- Updated to Farmer's Delight Refabricated 2.4.1
  - Switched maven repository from Greenhouse to Modrinth
- Bell Pepper Soup recipe rebalanced to require 3 bell peppers (down from 6)
- Bell Pepper now restores 2 hunger points instead of 1
- Bell Pepper Roll now requires Bell Pepper Slices, instead of whole Bell Peppers
- Honey Coffee now uses MilkCoffeeItem behavior (clears one effect on consumption)
- Pancake crafting recipes simplified and rebalanced
- Simplified Bell Pepper Pasta recipe to allow any combination of bell peppers

### Fixes
- Fixed broken Honey Coffee tooltip
- Added missing tooltip for Syrup Coffee
- Fixed Bell Pepper and Coffee crop models floating above the ground (now use Farmer's Delight's `crop_cross` model)

# 1.4.0
### Changes
- Removed another-mod suggestion from fabric.mod.json
- Added farmersdelight dependency to fabric.mod.json

# 1.3.2

### Fixes
- Implement conditional loading for Botany Pots and Farmer's Respite recipes, to prevent parsing errors on load


# 1.3.1

### Additions
- Added Bag of Roasted Coffee Beans
- Added Japanese translations (thank you EndilCrafter!)
- Added compatibility with Botany Pots:
  - Cotton, Coffee and Bell Peppers can now be grown in a Botany Pot
  - Wild Cotton, Wild Coffee and Wild Bell Peppers are also supported
- Added compatibility with Farmer's Respite:
    - Brew Farmer's Respite Coffee with Rustic Delight's Roasted Coffee Beans using a Kettle
    - Rustic Delight Coffee (and variants) can be brewed with Farmer's Respite Coffee Beans using the Cooking Pot
    - Rustic Delight Coffee variants can be crafted using Farmer's Respite Coffee
- Added compatibility with Supplementaries:
  - Cherry Blossom Cookie can now be put in a jar

### Changes
- Changed mod version to match Forge/NeoForge version
- Golden Coffee Beans can now also be crafted with Roasted Coffee Beans
- Roasted Coffee Beans can now be crafted into Brown Dye
- Made the following items compostable
    - Cherry Blossom Cookie
    - Cherry Blossom Cheesecake
    - Slice of Cherry Blossom Cheesecake
- Updated textures for Coffee Beans, Roasted Coffee Beans, Bag of Coffee Beans

### Fixes
- Fixed cutting recipe for wool having an empty tool


# 1.0.1

### Additions
- Added Taiwanese Mandarin translations (thank you Lobster0228!)

### Fixes
- Fixed bundle size being too big compared to other versions



# 1.0.0

### Additions
- First port to Fabric. Some features from the NeoForge version might still be missing and will be added at a later stage.