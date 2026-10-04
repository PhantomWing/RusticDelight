@~/Documents/Projects/Minecraft/MinecraftDeveloperPortal/.claude/profiles/fabric-loom.md

# Rustic Delight - `fabric/1.20`

**This file describes the `fabric/1.20` line**: Minecraft 1.20.1, Fabric.
Built with Architectury Loom standing in for Fabric Loom (see below).

It belongs to whichever folder has this branch checked out - the main `RusticDelight` folder or a worktree
under `RusticDelight/.worktrees/`. A session started in a worktree also loads the main folder's CLAUDE.md,
which describes another line; for this folder, this file is the one that applies. Confirm with
`git branch --show-current`. `MinecraftDeveloperPortal/data/mods.json` lists every line of the mod.

A rustic add-on for Farmer's Delight: coffee, cotton, bell peppers, pancakes, calamari and more.
Mod ID `rusticdelight`.

Split build: the Fabric lines are `fabric/*` (Loom); the NeoForge and Forge lines are `version/*`
(ModDevGradle, ForgeGradle), single-loader despite the prefix.

On the Fabric lines, Farmer's Delight Refabricated comes from the Modrinth maven as `<mc>-<fdr>`,
published against the base MC version (`dependency-sources.md`). REI and JEI availability lags the ladder; run
`/mc-check-deps` before assuming one exists for a rung.

## This line

- Built with Architectury Loom as a drop-in for Fabric Loom, because it honours `loom.ignoreDependencyLoomVersionValidation` (set in `gradle.properties`), which Fabric Loom does not expose: the Farmer's Delight Refabricated jar was built with a newer Loom.
- Java 17, Mojang mappings, no Parchment.
- Datagen: `runDatagen`. No generated resources are committed on this line. **Not pinned with `modId = mod_id`**: check its diff for files in the `minecraft` namespace (`fabric-loom.md`).
- No game tests yet. Writing the first one for whatever is ported next is the highest-value test available (`verification.md`).
- Published with `publishMods` from `build.gradle`, with the `-PpublishDryRun` flag. Uploads are tagged from `supported_minecraft_versions`.
- GitHub Actions: `build.yml`.
- Hand-authored access widener: `src/main/resources/rusticdelight.accesswidener`. A first suspect when a port fails to load.
